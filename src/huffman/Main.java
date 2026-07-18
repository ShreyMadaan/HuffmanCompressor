package huffman;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text to compress: ");
        String input = sc.nextLine();

        // ---------- COMPRESSION FLOW ----------

        // Frequency map
        Map<Character, Integer> freqMap =
                FrequencyMapBuilder.buildFreqMap(input);

        // Build Huffman Tree
        HuffmanNode root =
                HuffmanTreeBuilder.buildTree(freqMap);

        // Generate Huffman Codes
        Map<Character, String> huffmanCodes = new HashMap<>();
        HuffmanTreeBuilder.generateCodes(root, "", huffmanCodes);

        // Compress
        String compressedData =
                Compressor.compress(input, huffmanCodes);

        // Serialize Tree
        StringBuilder treeData = new StringBuilder();
        HuffmanTreeBuilder.serializeTree(root, treeData);

        // Write to file
        String filePath = "data.huff";
        FileHandler.writeToFile(
                filePath,
                treeData.toString(),
                compressedData
        );

        System.out.println("\nCompression completed.");
        System.out.println("Compressed file saved as: " + filePath);

        // ---------- DECOMPRESSION FLOW ----------

        // Read file
        CompressedFileData fileData =
                FileHandler.readFromFile(filePath);

        // Deserialize Huffman Tree
        HuffmanTreeDeserializer deserializer =
                new HuffmanTreeDeserializer();

        HuffmanNode rebuiltRoot =
                deserializer.deserialize(
                        fileData.getSerializedTree()
                );

        // Decompress
        String decompressedText =
                Decompressor.decompress(
                        fileData.getCompressedData(),
                        rebuiltRoot
                );

        // Output result
        System.out.println("\nDecompressed text:");
        System.out.println(decompressedText);

        // Optional verification
        System.out.println(
                "\nMatch with original: " +
                        input.equals(decompressedText)
        );
    }
}
