package filesystem;

import javax.swing.*;

public class SwingObserver extends FileStatsObserver {

    private StatsFrame frame;

    protected SwingObserver() {}

    public void showStats() {
        SwingUtilities.invokeLater(() -> {
            if (frame == null) frame = new StatsFrame(stats);
            else frame.update(stats);
            frame.setVisible(true);
        });
    }
}
