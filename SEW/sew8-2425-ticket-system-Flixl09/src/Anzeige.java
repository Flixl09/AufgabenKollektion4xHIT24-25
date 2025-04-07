import java.util.*;

public class Anzeige {
    private final Logger logger;
    private Map<Schalter, List<Integer>> schalterListMap;
    private TicketCounter ticketCounter;

    public Anzeige(TicketCounter ticketCounter, Logger logger) {
        this.logger = logger;
        this.schalterListMap = new HashMap<>();
        this.ticketCounter = ticketCounter;
    }

    private synchronized Schalter getSchalter(int nummer) {
        for (Map.Entry<Schalter, List<Integer>> entry : schalterListMap.entrySet()) {
            if (entry.getValue().contains(nummer)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public synchronized Schalter istMeineNummerDran(int nummer) {
        while (getSchalter(nummer) == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.logger.log("Anzeige: Nummer " + nummer + " ist dran");
        return getSchalter(nummer);
    }

    public synchronized void registriereSchalter(Schalter schalter) {
        this.logger.log("Anzeige: Schalter " + schalter + " wird registriert");
        schalterListMap.put(schalter, new ArrayList<>());
    }


    public synchronized void zeigeNummerAn(Schalter schalter) throws NoSuchElementException {
        int nummer = ticketCounter.getNextTicket();
        if (nummer == -1) {
            this.logger.log("Anzeige: Keine Nummern mehr");
            return;
        }
        schalterListMap.get(schalter).add(nummer);
        this.notifyAll();
        this.logger.log("Anzeige: Nummer " + nummer + " wird von " + schalter + " angezeigt");
    }
}
