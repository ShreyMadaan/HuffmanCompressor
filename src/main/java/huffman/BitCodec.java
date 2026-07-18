package huffman;

import java.io.ByteArrayOutputStream;
import java.util.Map;

/** Converts Huffman codes to and from packed bits. */
final class BitCodec {
    record EncodedBits(byte[] bytes, int bitCount) { }
    private BitCodec() { }
    static EncodedBits encode(String input, Map<Character, String> codes) {
        ByteArrayOutputStream output = new ByteArrayOutputStream(); int current = 0, used = 0, bitCount = 0;
        for (char symbol : input.toCharArray()) for (char bit : codes.get(symbol).toCharArray()) {
            current = (current << 1) | (bit - '0'); used++; bitCount++;
            if (used == 8) { output.write(current); current = 0; used = 0; }
        }
        if (used > 0) output.write(current << (8 - used));
        return new EncodedBits(output.toByteArray(), bitCount);
    }
    static String decode(byte[] bytes, int bitCount, HuffmanNode root, int expectedCharacters) {
        if (expectedCharacters == 0) return "";
        if (root == null) throw new IllegalArgumentException("Missing Huffman tree");
        if (root.isLeaf()) { if (bitCount != expectedCharacters) throw new IllegalArgumentException("Invalid single-symbol payload"); return String.valueOf(root.symbol()).repeat(expectedCharacters); }
        StringBuilder result = new StringBuilder(expectedCharacters); HuffmanNode node = root;
        for (int index = 0; index < bitCount; index++) {
            int bit = (bytes[index / 8] >>> (7 - index % 8)) & 1;
            node = bit == 0 ? node.left() : node.right();
            if (node == null) throw new IllegalArgumentException("Invalid payload bit path");
            if (node.isLeaf()) { result.append(node.symbol()); node = root; }
        }
        if (node != root || result.length() != expectedCharacters) throw new IllegalArgumentException("Truncated or invalid payload");
        return result.toString();
    }
}
