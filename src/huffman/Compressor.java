package huffman;

import java.util.Map;

public class Compressor {
    public static String compress(String input, Map<Character,String> huffmanCodes){
        if(input == null || input.isEmpty()){
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }

        if (huffmanCodes.size() == 1) {
            char onlyChar = huffmanCodes.keySet().iterator().next();
            return "0".repeat(input.length());
        }

        StringBuilder compressed = new StringBuilder();
        for(char c:input.toCharArray()){
            String code = huffmanCodes.get(c);

            if(code == null){
                throw new IllegalArgumentException("No Huffman code found, Invalid character: " + c);
            }
            compressed.append(code);
        }
        return compressed.toString();
    }
}
