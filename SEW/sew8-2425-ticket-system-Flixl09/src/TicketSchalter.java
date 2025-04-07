import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketSchalter implements Runnable, SchalterI {

    private TicketCounter ticketCounter;
    private BlockingDeque<Customer> schlange;
    private volatile boolean open;
    private final Lock lock;
    private final Logger logger;
    private Customer c;

    public TicketSchalter(TicketCounter ticketCounter, Logger logger) {
        this.open = true;
        this.schlange = new LinkedBlockingDeque<>();
        this.lock = new ReentrantLock();
        this.ticketCounter = ticketCounter;
        this.logger = logger;
    }

    public synchronized void ticketZiehen(Customer c) {
        while (true) try {
            schlange.put(c);
            this.notifyAll();
            break;
        } catch (InterruptedException _) {}
    }

    @Override
    public String toString() {
        return "TicketSchalter";
    }

    private synchronized Customer getNextCustomer() throws InterruptedException {
        while (schlange.isEmpty() && open) {
            try {
                wait();
            } catch (InterruptedException _) {}
        }
        if (schlange.isEmpty()) return null;
        return schlange.take();
    }

    @Override
    public void run() {
        while (open) {
            try {
                this.c = this.getNextCustomer();
                if (this.c == null) continue;
                int counter = this.ticketCounter.getTicket();
                this.c.setTicketNumber(counter);
                this.logger.log("Kunde " + c + " hat das Ticket erhalten");
                this.logger.log("Schalter wurden informiert");
            } catch (InterruptedException _) {}
        }
        this.logger.log("Ticketschalter wird geschlossen");
    }

    public synchronized void close() {
        open = false;
        this.notifyAll();
    }

}
