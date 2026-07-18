package huffman;

import java.util.Map;

public class Compressor {

    public static String compress(String input, Map<Character, String> huffmanCodes) {

        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }

        if (huffmanCodes == null || huffmanCodes.isEmpty()) {
            throw new IllegalArgumentException("Huffman codes cannot be null or empty");
        }

        // Special case: only one unique character
        if (huffmanCodes.size() == 1) {
            return "0".repeat(input.length());
        }

        StringBuilder compressed = new StringBuilder();

        for (char c : input.toCharArray()) {
            String code = huffmanCodes.get(c);

            if (code == null) {
                throw new IllegalArgumentException(
                        "No Huffman code found for character: " + c
                );
            }

            compressed.append(code);
        }

        return compressed.toString();
    }
}
