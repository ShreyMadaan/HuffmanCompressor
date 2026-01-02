package huffman;

import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTreeBuilder {

    public static HuffmanNode buildTree(Map<Character, Integer> freqMap) {
        // Edge case: empty input
        if (freqMap == null || freqMap.isEmpty()) {
            throw new IllegalArgumentException("Frequency map cannot be null or empty");
        }

        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();

        // Create leaf nodes
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Build Huffman Tree
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();

            HuffmanNode parent =
                    new HuffmanNode(left.getFreq() + right.getFreq(), left, right);

            pq.add(parent);
        }

        // Root of Huffman Tree
        return pq.poll();
    }

    // ---------- Tree Serialization ----------
    public static void serializeTree(HuffmanNode root, StringBuilder sb) {
        if (root == null) return;

        // Leaf node
        if (root.getLeft() == null && root.getRight() == null) {
            sb.append('1').append(root.getChar());
            return;
        }

        // Internal node
        sb.append('0');
        serializeTree(root.getLeft(), sb);
        serializeTree(root.getRight(), sb);
    }

    // ---------- Huffman Code Generation ----------
    public static void generateCodes(
            HuffmanNode root,
            String code,
            Map<Character, String> huffmanCodes
    ) {
        if (root == null) return;

        // Leaf node
        if (root.getLeft() == null && root.getRight() == null) {
            huffmanCodes.put(root.getChar(), code);
            return;
        }

        generateCodes(root.getLeft(), code + "0", huffmanCodes);
        generateCodes(root.getRight(), code + "1", huffmanCodes);
    }
}
