package huffman;

import java.util.HashMap;
import java.util.Map;

public class HuffmanCodeGenerator {
    public static Map<Character,String> generateCodes(HuffmanNode root){
        Map<Character,String> codes = new HashMap<>();

        if(root == null){
            throw new IllegalArgumentException("Root cannot be null");
        }

        generateCodesHelper(root, "", codes);
        return codes;
    }
    private static void generateCodesHelper
        (HuffmanNode node,
         String code,
         Map<Character,String> codes){

        if(node.getLeft() == null && node.getRight() == null){
            codes.put(node.getChar(), code);
            return;
        }
        if(node.getLeft() != null){
            generateCodesHelper(node.getLeft(), code + "0", codes);
        }
        if(node.getRight() != null){
            generateCodesHelper(node.getRight(), code + "1", codes);
        }
    }
}
