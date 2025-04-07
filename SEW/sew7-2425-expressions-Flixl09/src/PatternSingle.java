public abstract class PatternSingle implements Operation {
    protected Operation[] operands;

    /**
     * Instantiates a new Max.
     *
     * @param operands the operands
     */
    public PatternSingle(Operation... operands) {
        this.operands = operands;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildString(sb);
        return sb.toString();
    }
}
