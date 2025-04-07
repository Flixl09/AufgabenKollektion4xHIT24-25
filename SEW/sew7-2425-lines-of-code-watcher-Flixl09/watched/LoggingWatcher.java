package filesystem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LoggingWatcher extends Watcher {

    ArrayList<Observer> observers = new ArrayList<>();
    private File file = new File();













    
    

    public static void main(String[] args) {
        // Watcher kann mit try-with-resources verwendet werden:
//        try(
//                Watcher watcher = new LoggingWatcher(new File("./watched"));
//        ) {
//        } catch (IOException ex) {
//            ex.printStackTrace();
//
//        }

        // wenn der Watcher bestehen bleiben (und das Programm endlos weiterlaufen) soll,
        // kann ein Watcher auch ohne try-with-resources verwendet werden (trotz Warnung):
        Watcher watcher = new LoggingWatcher(new File("./watched"));
        watcher.attach(new CmdObserver());
        watcher.run();
    }
/*
    public LoggingWatcher(File dir) {
        super(dir);
    }
 */
    public LoggingWatcher(File dir) {
        super(dir);

    }
    @Override
    public void handleChange(File file, boolean exists) {
        System.out.println("File " + file + " " + (exists ? "created" : "deleted"));
        this.file = file;
        notifyObservers();
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(file);
        }
    }
}