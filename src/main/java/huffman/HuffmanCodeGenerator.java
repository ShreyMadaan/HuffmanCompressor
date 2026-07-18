package huffman;

import java.util.HashMap;
import java.util.Map;

/** Derives prefix-free bit codes from a tree. */
final class HuffmanCodeGenerator {
    private HuffmanCodeGenerator() { }
    static Map<Character, String> generate(HuffmanNode root) {
        Map<Character, String> codes = new HashMap<>();
        if (root != null) fill(root, "", codes);
        return codes;
    }
    private static void fill(HuffmanNode node, String prefix, Map<Character, String> codes) {
        if (node.isLeaf()) { codes.put(node.symbol(), prefix.isEmpty() ? "0" : prefix); return; }
        fill(node.left(), prefix + '0', codes);
        fill(node.right(), prefix + '1', codes);
    }
}
