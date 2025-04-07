/**
 * The type Multi.
 */
public class Multi extends Pattern {
    /**
     * Instantiates a new Multi.
     *
     * @param left  the left
     * @param right the right
     */
    public Multi(Operation left, Operation right) {
        super(left, right);
    }

    public double getValue() {
        return left.getValue() * right.getValue();
    }

    public boolean equals(Operation v) {
        if (v instanceof Multi multi) {
            return left.equals(multi.left) && right.equals(multi.right);
        }
        return false;
    }

    public void buildString(StringBuilder sb){
        sb.append("(");
        left.buildString(sb);
        sb.append(" * ");
        right.buildString(sb);
        sb.append(")");
    }
}
