package filesystem;

import java.io.BufferedReader;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class FileStatsObserver implements Observer {
    protected HashMap<String, HashMap<String, Object>> stats = new HashMap<>();

    private static final Map<String, CommentGetter> filetypes = Map.of(
            "java", new JavaComments(),
            "py", new PythonComments()
    );

    private CommentGetter lang;

    protected FileStatsObserver() {}


    public String getText(File f) {
        String text = "";
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(String.valueOf(f)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text += line + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }


    public void getFiletype(File f) {

        String[] parts = f.getName().split("\\.");
        String filetype = parts[parts.length - 1];
        if (stats.containsKey(f.getName())) {
            stats.get(f.getName()).put("filetype", filetype);
        } else {
            HashMap<String, Object> file = new HashMap<>();
            file.put("filetype", filetype);
            stats.put(f.getName(), file);
        }
        this.lang = filetypes.get(filetype) == null ? new CommentGetter() {
            @Override
            public int getComments() {
                return 0;
            }

            @Override
            public int getCode() {
                return 0;
            }

            @Override
            public void setText(String[] text) {

            }
        }: filetypes.get(filetype);

        if (filetypes.get(filetype) == null)
            this.lang = new CommentGetter() {
                @Override
                public int getComments() {
                    return 0;
                }

                @Override
                public int getCode() {
                    return 0;
                }

                @Override
                public void setText(String[] text) {

                }
            };
        else {
            this.lang = filetypes.get(filetype);
        }
    }

    public void getLines(File f) {
        int lines;

        String text = getText(f);
        lines = text.split("\n").length;

        if (stats.containsKey(f.getName())) {
            stats.get(f.getName()).put("lines", lines);
        } else {
            HashMap<String, Object> file = new HashMap<>();
            file.put("lines", lines);
            stats.put(f.getName(), file);
        }
    }

    public void getEmptyLines(File f) {

        int emptyLines = 0;
        String text = getText(f);
        for (String line : text.split("\n")) {
            if (line.trim().isEmpty()) {
                emptyLines++;
            }
        }

        if (stats.containsKey(f.getName())) {
            stats.get(f.getName()).put("emptyLines", emptyLines);
        } else {
            HashMap<String, Object> file = new HashMap<>();
            file.put("emptyLines", emptyLines);
            stats.put(f.getName(), file);

        }
    }

    public void getCodeLines(File f) {
        int codeLines = 0;

        codeLines = lang.getCode();


        if (stats.containsKey(f.getName())) {
            stats.get(f.getName()).put("codeLines", codeLines);
        } else {
            HashMap<String, Object> file = new HashMap<>();
            file.put("codeLines", codeLines);
            stats.put(f.getName(), file);
        }
    }

    public void getCommentLines(File f) {
        int commentLines = 0;

        commentLines = lang.getComments();

        if (stats.containsKey(f.getName())) {
            stats.get(f.getName()).put("commentLines", commentLines);
        } else {
            HashMap<String, Object> file = new HashMap<>();
            file.put("commentLines", commentLines);
            stats.put(f.getName(), file);
        }
    }

    public abstract void showStats();

    public void update(File f) {
        if (f.getName().contains("~")) return;
        if (!this.stats.isEmpty()) if (this.stats.containsKey(f.getName())) this.stats.get(f.getName()).clear();
        getFiletype(f);
        lang.setText(getText(f).split("\n"));
        getEmptyLines(f);
        getCommentLines(f);
        getCodeLines(f);
        getLines(f);
        showStats();
    }
}
