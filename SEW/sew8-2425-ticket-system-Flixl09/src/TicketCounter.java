import java.util.ArrayList;
import java.util.List;

public class TicketCounter {
    private int counter;
    private List<Integer> waitingTickets;

    public TicketCounter() {
        counter = 1;
        this.waitingTickets = new ArrayList<>();
    }

    public synchronized int getTicket() {
        int ticket = counter;
        counter++;
        this.waitingTickets.add(ticket);
        this.notifyAll();
        return ticket;
    }

    public synchronized int getNextTicket() {
        while (this.waitingTickets.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException _) {
                return -1;
            }
        }
        return this.waitingTickets.removeFirst();
    }

    @Override
    public String toString() {
        return "TicketCounter{" +
                "counter=" + counter +
                ", waitingTickets=" + waitingTickets +
                '}';
    }
}
