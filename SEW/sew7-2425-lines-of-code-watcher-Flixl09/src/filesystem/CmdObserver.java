package filesystem;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class CmdObserver extends FileStatsObserver {

    protected CmdObserver() {
    }

    public void showStats() {
        System.out.println("---------------------------------------------------------");
        System.out.println("Dateien  Zeilen  Davon Code  Davon Leerzeilen  Davon Kommentarzeilen");
        System.out.println("---------------------------------------------------------");
        super.stats.forEach((k, v) -> {
            System.out.printf("%-8s %-7d %-10d %-16d %-20d%n", v.get("filetype"), v.get("lines"), v.get("codeLines"), v.get("emptyLines"), v.get("commentLines"));
        });
    }
}
