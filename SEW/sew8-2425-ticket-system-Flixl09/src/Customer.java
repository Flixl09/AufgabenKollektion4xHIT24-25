import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Customer implements Runnable{
    private int ticketNumber;
    private final String name;
    private final Lock lock;
    private Anzeige anzeige;
    private boolean bearbeitet;
    private final List<SchalterI> schalterList;
    private final Logger logger;

    public Customer(String name, Anzeige anzeige, List<SchalterI> schalterList, Logger logger) {
        this.name = name;
        this.ticketNumber = 0;
        this.lock = new ReentrantLock();
        this.anzeige = anzeige;
        this.bearbeitet = false;
        this.schalterList = schalterList;
        this.logger = logger;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public synchronized void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
        if (ticketNumber != 0) this.logger.log("Kunde " + this + " hat Ticket Nr: " + ticketNumber);
        else this.logger.log("Kunde " + this + " hat kein Ticket mehr");
    }

    public synchronized void setBearbeitet(boolean bearbeitet) {
        this.bearbeitet = bearbeitet;
    }

    @Override
    public synchronized String toString() {
        String nr = ticketNumber == 0 ? "" : " Nr: " + ticketNumber;
        return name + nr;
    }

    @Override
    public void run() {
        while (ticketNumber != 0 || !bearbeitet) {
            if (ticketNumber == 0 && !bearbeitet) {
                for (SchalterI s : schalterList) {
                    if (s instanceof TicketSchalter) {
                        ((TicketSchalter) s).ticketZiehen(this);
                        this.logger.log("Kunde " + this + " hat sich für ein Ticket angestellt");
                    }
                }
            }
            if (ticketNumber != 0 && !bearbeitet) {
                Schalter s = anzeige.istMeineNummerDran(ticketNumber);
                if (s != null) {
                    this.logger.log("Kunde " + this + " hat das Ticket auf der Anzeige gesehen");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException _) {}
                    this.logger.log("Kunde " + this + " geht zum Schalter");
                    s.ticketBearbeiten(this);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException _) {}
        }
    }
}
