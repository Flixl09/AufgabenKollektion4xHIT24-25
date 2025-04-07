/**
 * The type Max.
 */
public class Max extends PatternSingle {
    /**
     * Instantiates a new Max.
     *
     * @param operands the operands
     */
    public Max(Operation... operands) {
        super(operands);
    }

    @Override
    public double getValue() {
        double max = Double.NEGATIVE_INFINITY;
        for (Operation operand : operands) {
            max = Math.max(max, operand.getValue());
        }
        return max;
    }

    @Override
    public boolean equals(Operation v) {
        if (v instanceof Max m) {
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
        sb.append("max(");
        for (int i = 0; i < operands.length; i++) {
            operands[i].buildString(sb);
            if (i < operands.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
    }
}
