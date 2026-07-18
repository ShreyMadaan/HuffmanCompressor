# Huffman Compressor

A dependency-free Java 17 command-line compressor using Huffman coding. Archives use a compact binary format and retain the frequency table required to recreate a deterministic tree.

```text
java -cp target/classes huffman.Main compress <input.txt> <output.huff>
java -cp target/classes huffman.Main decompress <input.huff> <output.txt>
```

Build with `mvn package`. To run the dependency-free self-test after compiling: `java -cp target/test-classes:target/classes huffman.HuffmanCompressionServiceTest` (use `;` instead of `:` on Windows). Empty files, single-symbol inputs, Unicode text, and corrupt archive validation are supported.
