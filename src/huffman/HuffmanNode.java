package huffman;

public class HuffmanNode implements Comparable<HuffmanNode>{
    char c;
    int freq;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(char c, int freq){
        this.c = c;
        this.freq = freq;
    }
    public HuffmanNode(int freq, HuffmanNode left, HuffmanNode right){
        this.freq = freq;
        this.left = left;
        this.right = right;

    }
    @Override
    public int compareTo(HuffmanNode other) {
        return this.freq - other.freq;
    }
}
