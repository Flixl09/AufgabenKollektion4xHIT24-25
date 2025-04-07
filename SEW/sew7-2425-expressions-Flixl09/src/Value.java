

/**
 * The type Value.
 */
public class Value implements Operation {
    private double value;

    /**
     * Instantiates a new Value.
     *
     * @param value the value
     */
    public Value(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void buildString(StringBuilder sb){
        sb.append(String.valueOf(value));
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public boolean equals(Operation v) {
        if (v instanceof Value) {
            Value a = (Value) v;
            return value == a.value;
        }
        return false;
    }
}
