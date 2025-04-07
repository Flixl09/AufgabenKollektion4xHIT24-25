/**
 * The type Subtract.
 */
public class Subtract extends Pattern {
    /**
     * Instantiates a new Subtract.
     *
     * @param left  the left
     * @param right the right
     */
    public Subtract(Operation left, Operation right) {
        super(left, right);
    }

    public double getValue() {
        return left.getValue() - right.getValue();
    }

    public void buildString(StringBuilder sb){
        sb.append("(");
        left.buildString(sb);
        sb.append(" - ");
        right.buildString(sb);
        sb.append(")");
    }

    public boolean equals(Operation v) {
        if (v instanceof Subtract sub) {
            return left.equals(sub.left) && right.equals(sub.right);
        }
        return false;
    }
}
