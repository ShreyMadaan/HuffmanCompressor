package huffman;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the text to compressor: ");
        String input = sc.nextLine();
        Map<Character,Integer>freqMap = FrequencyMapBuilder.buildFreqMap(input);
        System.out.println(freqMap);
        HuffmanNode root = HuffmanTreeBuilder.buildTree(freqMap);
        System.out.println(root.getFreq());

        Map<Character, String> codes = HuffmanCodeGenerator.generateCodes(root);
        System.out.println(codes);

    }
}
