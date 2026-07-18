package huffman;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

/** Application service coordinating compression, decompression, and file I/O. */
public final class HuffmanCompressionService {
    public CompressedFileData compress(String input) {
        Map<Character, Integer> frequencies = FrequencyMapBuilder.buildFreqMap(input);
        HuffmanNode tree = HuffmanTreeBuilder.build(frequencies);
        BitCodec.EncodedBits bits = BitCodec.encode(input, HuffmanCodeGenerator.generate(tree));
        return new CompressedFileData(frequencies, input.length(), bits.bitCount(), bits.bytes());
    }
    public String decompress(CompressedFileData archive) {
        if (archive == null) throw new IllegalArgumentException("Archive cannot be null");
        return BitCodec.decode(archive.payload(), archive.bitCount(), HuffmanTreeBuilder.build(archive.frequencies()), archive.originalLength());
    }
    public void compressFile(Path input, Path output, Charset charset) throws IOException {
        ArchiveFileHandler.write(output, compress(Files.readString(input, charset)));
    }
    public void decompressFile(Path input, Path output, Charset charset) throws IOException {
        Files.writeString(output, decompress(ArchiveFileHandler.read(input)), charset);
    }
    public void compressFile(Path input, Path output) throws IOException { compressFile(input, output, StandardCharsets.UTF_8); }
    public void decompressFile(Path input, Path output) throws IOException { decompressFile(input, output, StandardCharsets.UTF_8); }
}
