package filesystem;

import java.util.ArrayList;
import java.util.Arrays;

public class PythonComments implements CommentGetter {
    ArrayList<String> text = new ArrayList<>();

    @Override
    public int getComments() {
        int comments = 0;
        for (String line : text) {
            if (line.startsWith("#")) {
                comments++;
                text.remove(line);
            }
        }
        return comments;
    }

    @Override
    public int getCode() {
        int code = 0;
        for (String line : text) {
            if (!line.startsWith("#") && !line.isBlank()) {
                code++;
                text.remove(line);
            }
        }
        return code;
    }

    @Override
    public void setText(String[] text) {
        this.text = new ArrayList<>(Arrays.asList(text));
    }
}
