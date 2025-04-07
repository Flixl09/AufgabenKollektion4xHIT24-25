/**
 * The type Negate.
 */
public class Negate implements Operation {
    private Operation le;

    private StringBuilder sb;

    /**
     * Instantiates a new Negate.
     *
     * @param e the e
     */
    public Negate(Operation e) {
        le = e;
    }

    public double getValue() {
        return -le.getValue();
    }

    public void buildString(StringBuilder sb){
        sb.append("(-");
        le.buildString(sb);
        sb.append(")");
    }

    @Override
    public String toString() {
        sb = new StringBuilder();
        buildString(sb);
        return sb.toString();
    }

    public boolean equals(Operation v) {
        return v.getValue() == getValue();
    }
}
