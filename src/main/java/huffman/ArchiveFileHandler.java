package huffman;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

/** Persistence adapter for the versioned binary archive format. */
public final class ArchiveFileHandler {
    private static final int MAGIC = 0x48554631; // HUF1
    private static final int VERSION = 1;
    private ArchiveFileHandler() { }

    public static void write(Path target, CompressedFileData archive) throws IOException {
        if (target == null || archive == null) throw new IllegalArgumentException("Target and archive are required");
        Path parent = target.toAbsolutePath().getParent(); if (parent != null) Files.createDirectories(parent);
        try (DataOutputStream out = new DataOutputStream(Files.newOutputStream(target))) {
            out.writeInt(MAGIC); out.writeByte(VERSION); out.writeInt(archive.originalLength());
            out.writeShort(archive.frequencies().size());
            for (Map.Entry<Character, Integer> entry : new TreeMap<>(archive.frequencies()).entrySet()) { out.writeChar(entry.getKey()); out.writeInt(entry.getValue()); }
            out.writeInt(archive.bitCount()); out.writeInt(archive.payload().length); out.write(archive.payload());
        }
    }
    public static CompressedFileData read(Path source) throws IOException {
        if (source == null) throw new IllegalArgumentException("Source is required");
        try (DataInputStream in = new DataInputStream(Files.newInputStream(source))) {
            if (in.readInt() != MAGIC || in.readUnsignedByte() != VERSION) throw new IllegalArgumentException("Unsupported Huffman archive");
            int originalLength = in.readInt(); int count = in.readUnsignedShort();
            if (originalLength < 0 || (originalLength == 0 && count != 0) || (originalLength > 0 && count == 0)) throw new IllegalArgumentException("Invalid archive header");
            Map<Character, Integer> frequencies = new TreeMap<>(); long total = 0;
            for (int i = 0; i < count; i++) { char symbol = in.readChar(); int frequency = in.readInt(); if (frequency <= 0 || frequencies.put(symbol, frequency) != null) throw new IllegalArgumentException("Invalid frequency table"); total += frequency; }
            int bitCount = in.readInt(); int byteCount = in.readInt();
            if (bitCount < 0 || byteCount < 0 || byteCount != (bitCount + 7L) / 8 || total != originalLength) throw new IllegalArgumentException("Invalid archive lengths");
            byte[] payload = in.readNBytes(byteCount); if (payload.length != byteCount || in.read() != -1) throw new IllegalArgumentException("Truncated or trailing archive data");
            return new CompressedFileData(frequencies, originalLength, bitCount, payload);
        } catch (EOFException exception) { throw new IllegalArgumentException("Truncated Huffman archive", exception); }
    }
}
