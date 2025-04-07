package filesystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class JavaComments implements CommentGetter {

    @Override
    public void setText(String[] text) {
        this.text = new ArrayList<>(Arrays.asList(text));
    }

    ArrayList<String> text;

    @Override
    public int getComments() {
        int comments = 0;
        boolean mehrzeilig = false;
        ArrayList<String> remove = new ArrayList<>();
        for (String line : text) {
            if (line.trim().startsWith("//")) {
                comments++;
                remove.add(line);
                continue;
            }
            if (!line.trim().replaceAll("[^\"/]+", "").contains("\"//\"") && line.trim().contains("//")) {
                comments++;
                continue;
            }
            if (line.trim().contains("/*")) {
                mehrzeilig = true;
            }
            if (line.trim().contains("*/")) {
                mehrzeilig = false;
                comments++;
                remove.add(line);
                continue;
            }
            if (mehrzeilig) {
                comments++;
                remove.add(line);
            }
        }
        remove.forEach(s -> {
            int i = text.indexOf(s);
            if (i > -1) {
                text.remove(i);
            }
        });
        return comments;
    }

    @Override
    public int getCode() {
        int code = 0;
        for (String line : this.text) {
            if (line != null){
                if (!line.trim().isEmpty())
                    code++;
            }
        }
        return code;
    }
}
