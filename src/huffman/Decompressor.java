package huffman;

public class Decompressor {

    public static String decompress(String compressedData, HuffmanNode root) {

        // Validate inputs first
        if (root == null) {
            throw new IllegalArgumentException("Root cannot be null");
        }

        if (compressedData == null || compressedData.isEmpty()) {
            throw new IllegalArgumentException("Compressed data cannot be null or empty");
        }

        // Special case: single unique character
        if (root.getLeft() == null && root.getRight() == null) {
            return String.valueOf(root.getChar())
                    .repeat(compressedData.length());
        }

        StringBuilder result = new StringBuilder();
        HuffmanNode current = root;

        for (char bit : compressedData.toCharArray()) {

            if (bit == '0') {
                current = current.getLeft();
            } else if (bit == '1') {
                current = current.getRight();
            } else {
                throw new IllegalArgumentException("Invalid bit: " + bit);
            }

            if (current == null) {
                throw new IllegalArgumentException("Invalid compressed data");
            }

            // Leaf reached
            if (current.getLeft() == null && current.getRight() == null) {
                result.append(current.getChar());
                current = root;
            }
        }

        // Optional safety check
        if (current != root) {
            throw new IllegalArgumentException(
                    "Compressed data ended prematurely"
            );
        }

        return result.toString();
    }
}
