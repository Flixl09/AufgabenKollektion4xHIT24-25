/**
 * The type Min.
 */
public class Min extends PatternSingle {

    /**
     * Instantiates a new Min.
     *
     * @param operands the operands
     */
    public Min(Operation... operands) {
        this.operands = operands;
    }

    @Override
    public double getValue() {
        double min = Double.POSITIVE_INFINITY;
        for (Operation operand : operands) {
            min = Math.min(min, operand.getValue());
        }
        return min;
    }

    @Override
    public boolean equals(Operation v) {
        if (v instanceof Min m) {
            if (operands.length != m.operands.length) {
                return false;
            }
            for (int i = 0; i < operands.length; i++) {
                if (!operands[i].equals(m.operands[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public void buildString(StringBuilder sb) {
        sb.append("min(");
        for (int i = 0; i < operands.length; i++) {
            operands[i].buildString(sb);
            if (i < operands.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
    }
}
