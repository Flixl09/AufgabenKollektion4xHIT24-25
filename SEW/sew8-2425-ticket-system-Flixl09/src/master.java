import java.util.*;
import java.util.concurrent.*;

public class master {
    public static void main(String[] args) {
        Logger logger = new ConsoleLogger();
        TicketCounter ticketCounter = new TicketCounter();
        Anzeige anzeige = new Anzeige(ticketCounter, logger);

        TicketSchalter ts = new TicketSchalter(ticketCounter ,logger);
        Schalter s1 = new Schalter("Anita", logger, anzeige);
        Schalter s2 = new Schalter("Berta", logger, anzeige);
        Schalter s3 = new Schalter("Carla", logger, anzeige);

        List<SchalterI> schalterList = new LinkedList<>();
        schalterList.add(ts);
        schalterList.add(s1);
        schalterList.add(s2);
        schalterList.add(s3);

        Customer c1 = new Customer("Alice", anzeige, schalterList, logger);
        Customer c2 = new Customer("Bob", anzeige, schalterList, logger);
        Customer c3 = new Customer("Charlie", anzeige, schalterList, logger);
        Customer c4 = new Customer("David", anzeige, schalterList, logger);
        Customer c5 = new Customer("Eve", anzeige, schalterList, logger);
        Customer c6 = new Customer("Frank", anzeige, schalterList, logger);
        Customer c7 = new Customer("Grace", anzeige, schalterList, logger);

        List<Runnable> runnableList = new LinkedList<>();
        runnableList.add(ts);
        runnableList.add(s1);
        runnableList.add(s2);
        runnableList.add(s3);
        runnableList.add(c1);
        runnableList.add(c2);
        runnableList.add(c3);
        runnableList.add(c4);
        runnableList.add(c5);
        runnableList.add(c6);
        runnableList.add(c7);

        List<Thread> threadList = new ArrayList<>();

        for (Runnable r : runnableList) {
            Thread t = new Thread(r, r.toString());
            t.start();
            threadList.add(t);
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException _) {}


        System.out.println("Schalter werden geschlossen");
        for (Runnable t : runnableList) {
            if (t instanceof TicketSchalter) {
                ((TicketSchalter) t).close();
            } else if (t instanceof Schalter) {
                ((Schalter) t).close();
            }
        }

        System.out.println("Systeme werden heruntergefahren");
        for (Thread t : threadList) {
            if (t.isAlive())
                t.interrupt();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException _) {}

        for (Thread t : threadList) {
            System.out.println(t.getName());
            System.out.println(t.getState().name());
        }


        System.out.println("Alle Schalter sind geschlossen");
    }
}
