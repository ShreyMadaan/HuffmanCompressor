package huffman;

public class HuffmanNode implements Comparable<HuffmanNode>{
    private char c;
    private int freq;
    private HuffmanNode left;
    private HuffmanNode right;

    public HuffmanNode(char c, int freq){
        this.c = c;
        this.freq = freq;
    }
    public HuffmanNode(int freq, HuffmanNode left, HuffmanNode right){
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public char getChar(){
        return c;
    }
    public int getFreq(){
        return freq;
    }
    public HuffmanNode getLeft(){
        return left;
    }
    public HuffmanNode getRight(){
        return right;
    }
    @Override
    public int compareTo(HuffmanNode other) {
        return Integer.compare(this.freq, other.freq);
    }
}
