package huffman;

import java.util.Map;
import java.util.PriorityQueue;

/** Creates deterministic Huffman trees from frequency tables. */
public final class HuffmanTreeBuilder {
    private HuffmanTreeBuilder() { }
    static HuffmanNode build(Map<Character, Integer> frequencies) {
        if (frequencies == null) throw new IllegalArgumentException("Frequency map cannot be null");
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();
        frequencies.forEach((symbol, frequency) -> {
            if (symbol == null || frequency == null || frequency <= 0) throw new IllegalArgumentException("Invalid frequency table");
            queue.add(new HuffmanNode(symbol, frequency));
        });
        while (queue.size() > 1) queue.add(new HuffmanNode(queue.remove(), queue.remove()));
        return queue.poll(); // null intentionally represents an empty input
    }
}
