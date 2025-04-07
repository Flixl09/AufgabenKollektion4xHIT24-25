/**
 * The type Div.
 */
public class Div extends Pattern {
    /**
     * Instantiates a new Div.
     *
     * @param left  the left
     * @param right the right
     */
    public Div(Operation left, Operation right) {
        super(left, right);
    }

    @Override
    public double getValue() {
        return left.getValue() / right.getValue();
    }

    @Override
    public boolean equals(Operation v) {
        if (v instanceof Div) {
            return left.equals(((Div) v).left) && right.equals(((Div) v).right);
        }
        return false;
    }

    public void buildString(StringBuilder sb){
        sb.append("(");
        left.buildString(sb);
        sb.append(" / ");
        right.buildString(sb);
        sb.append(")");
    }
}
