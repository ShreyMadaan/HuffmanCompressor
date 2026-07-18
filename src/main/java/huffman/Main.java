package huffman;

import java.io.IOException;
import java.nio.file.Path;

/** Command-line entry point. */
public final class Main {
    private Main() { }
    public static void main(String[] args) {
        if (args.length != 3 || !(args[0].equals("compress") || args[0].equals("decompress"))) {
            System.err.println("Usage: java huffman.Main <compress|decompress> <input> <output>"); System.exit(2); return;
        }
        try {
            HuffmanCompressionService service = new HuffmanCompressionService();
            if (args[0].equals("compress")) service.compressFile(Path.of(args[1]), Path.of(args[2]));
            else service.decompressFile(Path.of(args[1]), Path.of(args[2]));
            System.out.println((args[0].equals("compress") ? "Compressed" : "Decompressed") + " successfully: " + args[2]);
        } catch (IOException | IllegalArgumentException exception) {
            System.err.println("Operation failed: " + exception.getMessage()); System.exit(1);
        }
    }
}
