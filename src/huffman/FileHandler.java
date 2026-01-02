package huffman;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

    // Write compressed data to file
    public static void writeToFile(
            String filePath,
            String serializedTree,
            String compressedData
    ) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(serializedTree);
            writer.newLine();
            writer.write(compressedData);
        } catch (IOException e) {
            throw new RuntimeException("Error writing compressed file", e);
        }
    }

    // Read compressed file
    public static CompressedFileData readFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String serializedTree = reader.readLine();
            String compressedData = reader.readLine();

            if (serializedTree == null || compressedData == null) {
                throw new IllegalArgumentException("Invalid compressed file format");
            }

            return new CompressedFileData(serializedTree, compressedData);

        } catch (IOException e) {
            throw new RuntimeException("Error reading compressed file", e);
        }
    }
}
