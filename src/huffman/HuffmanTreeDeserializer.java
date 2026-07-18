package huffman;

import org.w3c.dom.Node;

public class HuffmanTreeDeserializer {
    private int index = 0;

    public HuffmanNode deserialize(String data) {
        char marker = data.charAt(index++);

        // Leaf node
        if (marker == '1') {
            char ch = data.charAt(index++);
            return new HuffmanNode(ch, 0);
        }

        // Internal node
        HuffmanNode left = deserialize(data);
        HuffmanNode right = deserialize(data);

        return new HuffmanNode(0, left, right);
    }
}
