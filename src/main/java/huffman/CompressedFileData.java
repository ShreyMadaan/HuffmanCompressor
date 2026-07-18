package huffman;

import java.util.Map;

/** In-memory representation of a Huffman archive. */
public record CompressedFileData(Map<Character, Integer> frequencies, int originalLength, int bitCount, byte[] payload) {
    public CompressedFileData {
        frequencies = Map.copyOf(frequencies); payload = payload.clone();
    }
    @Override public byte[] payload() { return payload.clone(); }
}
