package huffman;

import org.w3c.dom.Node;

import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTreeBuilder {
    public static HuffmanNode buildTree(Map<Character,Integer> freqMap){
        // Edge case: empty input
        if(freqMap == null || freqMap.isEmpty()){
            throw new IllegalArgumentException("Frequency map cannot be null or empty");
        }

        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();

        // Create leaf nodes for each character and add to priority queue
        for(Map.Entry<Character,Integer> entry: freqMap.entrySet()){
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
        // Build the Huffman Tree
        while(pq.size() > 1){
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode parent = new HuffmanNode(left.getFreq() + right.getFreq(), left, right);
            pq.add(parent);
        }
        //Root of Huffman Tree
        return pq.poll();
    }

    public void serializeTree(HuffmanNode root, StringBuilder sb){
        if(root == null){
            return;
        }
        if (root.getLeft() == null && root.getRight() == null) {
            sb.append("1").append(root.getChar());
            return;
        }

        sb.append("0");
        serializeTree(root.getLeft(), sb);
        serializeTree(root.getRight(), sb);
    }
}
