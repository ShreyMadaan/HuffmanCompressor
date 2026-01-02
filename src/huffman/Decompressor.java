package huffman;

public class Decompressor {
    public static String decompress(String compressedData, HuffmanNode root){
        if(compressedData == null || compressedData.isEmpty()){
            throw new IllegalArgumentException("Compressed data cannot be null or empty");
        }
        if(root == null){
            throw new IllegalArgumentException("Root cannot be null");
        }

        StringBuilder result = new StringBuilder();
        HuffmanNode current = root;
        for(char bit:compressedData.toCharArray()){
            if(bit == '0'){
                current = current.getLeft();
            }else if(bit == '1'){
                current = current.getRight();
            }else{
                throw new IllegalArgumentException("Invalid bit: " + bit);
            }

            if(current.getLeft() == null && current.getRight() == null){
                result.append(current.getChar());
                current = root;
            }
        }
        return result.toString();
    }
}
