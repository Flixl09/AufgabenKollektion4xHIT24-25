/**
 * The interface Operation.
 */
public interface Operation {
    /**
     * Gets value.
     *
     * @return the value
     */
    public double getValue();

    public String toString();

    public void buildString(StringBuilder sb);

    /**
     * Equals boolean.
     *
     * @param v the v
     * @return the boolean
     */
    public boolean equals(Operation v);
}
