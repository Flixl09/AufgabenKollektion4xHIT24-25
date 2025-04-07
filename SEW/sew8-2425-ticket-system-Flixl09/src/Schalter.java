import java.util.concurrent.locks.ReentrantLock;

public class Schalter implements Runnable, SchalterI {
    private final String Mitarbeiter;
    private volatile boolean open;
    private final Logger logger;
    private final Anzeige anzeige;
    private boolean waiting;

    public Schalter(String Mitarbeiter, Logger logger, Anzeige anzeige) {
        this.Mitarbeiter = Mitarbeiter;
        this.open = true;
        this.logger = logger;
        this.anzeige = anzeige;
        this.anzeige.registriereSchalter(this);
        this.waiting = false;

    }

    public synchronized void ticketBearbeiten(Customer c) {
        c.setTicketNumber(0);
        this.logger.log("Mitarbeiter " + Mitarbeiter + " bearbeitet das Ticket von " + c);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException _) {}
        this.logger.log("Mitarbeiter " + Mitarbeiter + " hat das Ticket " + c + " bearbeitet");
        c.setBearbeitet(true);
        this.waiting = false;
        this.notifyAll();
    }

    private synchronized void waitingForClient() {
        while (waiting) {
            if (!open)
                return;
            try {
                System.out.println(Mitarbeiter + " wartet auf Kunde");
                this.wait();
            } catch (InterruptedException _) {}
        }
        waiting = false;
    }

    @Override
    public String toString() {
        return "Schalter{" +
                "Mitarbeiter='" + Mitarbeiter + "'}";
    }

    @Override
    public void run() {
        while (open) {
            this.waitingForClient();
            if (!open)
                break;
            this.anzeige.zeigeNummerAn(this);
            this.waiting = true;
        }
        this.logger.log("Schalter " + Mitarbeiter + " wird geschlossen");
    }

    public synchronized void close() {
        open = false;
        this.notifyAll();
    }
}
