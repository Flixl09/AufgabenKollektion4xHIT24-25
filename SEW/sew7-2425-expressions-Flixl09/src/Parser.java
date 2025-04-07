import javax.swing.*;

public class Parser {
    protected ParserType parser;

    protected String s;

    public Parser(String s, ParserType p) {
        this.parser = p;
        this.s = s;
    }

    public Double parse() {
        Operation ops = parser.parse(s);

        return ops.getValue();
    }

    public ParserType getParser() {
        return parser;
    }

    public void setParser(ParserType parser) {
        this.parser = parser;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
