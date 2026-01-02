package huffman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHandler {
    public static String readFile(String path) throws IOException {
        return Files.readString(Path.of(path));
    }

    public static void writeFile(String path, String content) throws IOException {
        Files.writeString(Path.of(path), content);
    }
}
