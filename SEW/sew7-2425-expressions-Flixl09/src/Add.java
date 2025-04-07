/**
 * The type Add.
 */
public class Add extends Pattern {
    /**
     * Instantiates a new Add.
     *
     * @param left  the left
     * @param right the right
     */
    public Add(Operation left, Operation right) {
        super(left, right);
    }

    public double getValue() {
        return left.getValue() + right.getValue();
    }

    public void buildString(StringBuilder sb){
        sb.append("(");
        left.buildString(sb);
        sb.append(" + ");
        right.buildString(sb);
        sb.append(")");
    }


    public boolean equals(Operation v) {
        if (v instanceof Add) {
            Add a = (Add) v;
            return left.equals(a.left) && right.equals(a.right);
        }
        return false;
    }
}
