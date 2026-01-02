package huffman;

public class CompressedFileData {

    private final String serializedTree;
    private final String compressedData;

    public CompressedFileData(String serializedTree, String compressedData) {
        this.serializedTree = serializedTree;
        this.compressedData = compressedData;
    }

    public String getSerializedTree() {
        return serializedTree;
    }

    public String getCompressedData() {
        return compressedData;
    }
}
