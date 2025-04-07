/**
 * The type Potenz.
 */
public class Potenz extends Pattern {

    /**
     * Instantiates a new Potenz.
     *
     * @param left  the left
     * @param right the right
     */
    public Potenz(Operation left, Operation right) {
        super(left, right);
    }

    @Override
    public double getValue() {
        return Math.pow(left.getValue(), right.getValue());
    }

    public void buildString(StringBuilder sb){
        sb.append("(");
        left.buildString(sb);
        sb.append(" ^ ");
        right.buildString(sb);
        sb.append(")");
    }


    @Override
    public boolean equals(Operation v) {
        if (v instanceof Potenz p) {
            return left.equals(p.left) && right.equals(p.right);
        }
        return false;
    }

}
