package huffman;

/** Immutable node in a Huffman tree. */
final class HuffmanNode implements Comparable<HuffmanNode> {
    private final Character symbol;
    private final int frequency;
    private final HuffmanNode left;
    private final HuffmanNode right;
    private final char smallestSymbol;

    HuffmanNode(char symbol, int frequency) {
        if (frequency <= 0) throw new IllegalArgumentException("Frequency must be positive");
        this.symbol = symbol; this.frequency = frequency; this.left = null; this.right = null; this.smallestSymbol = symbol;
    }
    HuffmanNode(HuffmanNode left, HuffmanNode right) {
        if (left == null || right == null) throw new IllegalArgumentException("Internal nodes need two children");
        this.symbol = null; this.frequency = Math.addExact(left.frequency, right.frequency);
        this.left = left; this.right = right; this.smallestSymbol = (char) Math.min(left.smallestSymbol, right.smallestSymbol);
    }
    boolean isLeaf() { return symbol != null; }
    char symbol() { if (!isLeaf()) throw new IllegalStateException("Internal node has no symbol"); return symbol; }
    int frequency() { return frequency; }
    HuffmanNode left() { return left; }
    HuffmanNode right() { return right; }
    @Override public int compareTo(HuffmanNode other) {
        int byFrequency = Integer.compare(frequency, other.frequency);
        return byFrequency != 0 ? byFrequency : Character.compare(smallestSymbol, other.smallestSymbol);
    }
}
