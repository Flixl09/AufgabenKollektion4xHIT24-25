package filesystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class StatsFrame extends JFrame {

    public StatsFrame(HashMap<String, HashMap<String, Object>> stats) {
        setTitle("File Statistics");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"File Name", "File Type", "Lines","Empty Lines", "Code Lines", "Comment Lines"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Map.Entry<String, HashMap<String, Object>> entry : stats.entrySet()) {
            String fileName = entry.getKey();
            HashMap<String, Object> fileStats = entry.getValue();
            String fileType = (String) fileStats.getOrDefault("filetype", "N/A");
            int emptyLines = (int) fileStats.getOrDefault("emptyLines", 0);
            int codeLines = (int) fileStats.getOrDefault("codeLines", 0);
            int lines = (int) fileStats.getOrDefault("lines", 0);
            int commentLines = (int) fileStats.getOrDefault("commentLines", 0);

            model.addRow(new Object[]{fileName, fileType, lines, emptyLines, codeLines, commentLines});
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void update(HashMap<String, HashMap<String, Object>> stats) {
        DefaultTableModel model = (DefaultTableModel) ((JTable) ((JScrollPane) getContentPane().getComponent(0)).getViewport().getView()).getModel();
        model.setRowCount(0);

        for (Map.Entry<String, HashMap<String, Object>> entry : stats.entrySet()) {
            String fileName = entry.getKey();
            HashMap<String, Object> fileStats = entry.getValue();
            String fileType = (String) fileStats.getOrDefault("filetype", "N/A");
            int emptyLines = (int) fileStats.getOrDefault("emptyLines", 0);
            int codeLines = (int) fileStats.getOrDefault("codeLines", 0);
            int lines = (int) fileStats.getOrDefault("lines", 0);
            int commentLines = (int) fileStats.getOrDefault("commentLines", 0);

            model.addRow(new Object[]{fileName, fileType, lines, emptyLines, codeLines, commentLines});
        }

        model.fireTableDataChanged();
    }
}