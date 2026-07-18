package huffman;

/** Lightweight self-test that needs no third-party test framework. */
public final class HuffmanCompressionServiceTest {
    public static void main(String[] args) {
        HuffmanCompressionService service = new HuffmanCompressionService();
        for (String text : new String[] { "", "aaaaaa", "banana bandana", "Hello, नमस्ते 👋\n" }) {
            String decoded = service.decompress(service.compress(text));
            if (!text.equals(decoded)) throw new AssertionError("Round trip failed for: " + text);
        }
        System.out.println("All Huffman round-trip tests passed.");
    }
}
