/**
 * The type Pattern.
 */
public abstract class Pattern implements Operation{

    /**
     * The Left.
     */
    protected Operation left;
    /**
     * The Right.
     */
    protected Operation right;

    /**
     * Instantiates a new Pattern.
     *
     * @param left  the left
     * @param right the right
     */
    public Pattern(Operation left, Operation right){
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildString(sb);
        return sb.toString();
    }
}
