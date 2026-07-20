# Huffman Compressor

A dependency-free **Java 17** command-line file compressor/decompressor based on **Huffman Coding**.

This project builds a deterministic Huffman tree from input byte frequencies, writes a compact binary archive that includes metadata needed for reconstruction, and restores the original file losslessly during decompression.

---

## ✨ Features

- ✅ Lossless compression using Huffman coding
- ✅ Deterministic tree reconstruction during decompression
- ✅ Compact custom binary archive format (`.huff`)
- ✅ No third-party runtime dependencies
- ✅ Simple CLI interface (`compress` / `decompress`)
- ✅ Clean modular architecture (tree building, code generation, bit codec, archive handling)
- ✅ Self-test class included for quick verification

---

## 🧱 Project Structure

```text
HuffmanCompressor/
├── pom.xml
├── README.md
└── src
    ├── main/java/huffman
    │   ├── Main.java
    │   ├── HuffmanCompressionService.java
    │   ├── FrequencyMapBuilder.java
    │   ├── HuffmanTreeBuilder.java
    │   ├── HuffmanNode.java
    │   ├── HuffmanCodeGenerator.java
    │   ├── BitCodec.java
    │   ├── ArchiveFileHandler.java
    │   └── CompressedFileData.java
    └── test/java/huffman
        └── HuffmanCompressionServiceTest.java
```

---

## ⚙️ Requirements

- **Java 17+**
- **Maven 3.8+** (recommended)

---

## 🚀 Build

```bash
mvn clean package
```

This compiles source and test classes under `target/classes` and `target/test-classes`.

---

## 🖥️ Usage

### Compress a file

```bash
java -cp target/classes huffman.Main compress <input.txt> <output.huff>
```

### Decompress a file

```bash
java -cp target/classes huffman.Main decompress <input.huff> <output.txt>
```

### Example

```bash
java -cp target/classes huffman.Main compress sample.txt sample.huff
java -cp target/classes huffman.Main decompress sample.huff restored.txt
```

You can then compare:

```bash
diff sample.txt restored.txt
```

(Windows users can use `fc sample.txt restored.txt`.)

---

## ✅ Run the Self-Test

After building:

```bash
java -cp target/test-classes:target/classes huffman.HuffmanCompressionServiceTest
```

> On **Windows**, use `;` instead of `:` in classpath:
>
> ```bash
> java -cp target/test-classes;target/classes huffman.HuffmanCompressionServiceTest
> ```

---

## 🧠 How It Works (High-Level)

1. **Read input bytes**
2. **Build frequency map** of each byte value
3. **Construct Huffman tree** using a priority queue (greedy merge)
4. **Generate variable-length prefix codes** for bytes
5. **Encode data into packed bits**
6. **Write archive** including:
   - encoded bitstream
   - metadata (enough to reconstruct the same tree/code map)
7. During decompression:
   - load metadata
   - rebuild tree/codes deterministically
   - decode bits back to original bytes

---

## 🏗️ Core Components

- **`Main`**  
  CLI entry point. Parses command arguments and dispatches compression/decompression.

- **`HuffmanCompressionService`**  
  Main orchestration layer for compress/decompress workflows.

- **`FrequencyMapBuilder`**  
  Computes byte frequency table from input data.

- **`HuffmanTreeBuilder`**  
  Builds Huffman tree from frequencies using priority queue strategy.

- **`HuffmanNode`**  
  Tree node model (symbol/frequency/left/right), comparable for queue ordering.

- **`HuffmanCodeGenerator`**  
  Traverses tree to assign binary prefix codes to each symbol.

- **`BitCodec`**  
  Handles bit-level packing/unpacking between code strings and byte arrays.

- **`ArchiveFileHandler`**  
  Reads/writes custom archive binary format and metadata.

- **`CompressedFileData`**  
  Data carrier for compressed payload + reconstruction metadata.

- **`HuffmanCompressionServiceTest`**  
  Lightweight end-to-end round-trip verification.

---

## 📦 Archive Format Notes

The output archive stores both:

- compressed bit payload
- frequency/header metadata needed for deterministic decoding

This allows decompression **without external dictionaries** and ensures the exact original file can be restored.

---

## 🧪 Suggested Manual Tests

- Empty file
- Single repeated character/file with low entropy
- Typical text file (source code, JSON, logs)
- Binary-like content (if supported by current IO path)
- Large file performance smoke test
- Corrupt/truncated archive behavior

---

## ⚠️ Limitations / Current Scope

- CLI-oriented (no GUI)
- Uses custom `.huff` format (not interoperable with standard ZIP/GZIP)
- Error messages/validation may be minimal for malformed inputs (can be improved)
- Performance is focused on clarity and correctness over advanced optimizations

---

## 🛠️ Future Improvements

- Better CLI UX (`--help`, flags, overwrite control)
- Stream-based processing for very large files
- Richer error handling and archive integrity checks
- Compression statistics output (ratio, time, entropy estimate)
- JUnit-based automated tests and edge-case coverage
- Optional canonical Huffman encoding for even smaller headers

---

## 📚 Concepts Used

- Greedy algorithms
- Priority queue / min-heap
- Binary trees
- Prefix-free coding
- Bitwise encoding/decoding
- File I/O and binary serialization in Java

---

## 🤝 Contributing

Contributions are welcome!  
If you want to extend functionality or improve performance/testing:

1. Fork the repo
2. Create a feature branch
3. Commit your changes
4. Open a pull request with a clear description

---

## 📄 License

No license file is currently present in this repository.  
If you plan to share or reuse this project publicly, consider adding a license (e.g., MIT).

---

## 👤 Author

**Shrey Madaan**  
Repository: [ShreyMadaan/HuffmanCompressor](https://github.com/ShreyMadaan/HuffmanCompressor)
