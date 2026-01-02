Huffman Compressor (Java)
A complete Huffman Coding–based text compression and decompression system implemented in Java.
The project demonstrates efficient lossless compression using priority queues, binary trees, and clean object-oriented design.


Features
Builds frequency map from input text
Constructs Huffman Tree using Min-Heap (PriorityQueue)
Generates prefix-free Huffman codes
Compresses text into binary string
Serializes Huffman Tree for persistence
Saves compressed data to .huff file
Fully reconstructs and decompresses original text
Handles edge cases (single character, invalid data)


Core Concepts Used
Greedy Algorithms
Binary Trees
Priority Queue (Min Heap)
Recursion
File I/O
Clean Architecture & Single Responsibility Principle


Project Structure
HuffmanCompressor/
│
├── src/
│   └── huffman/
│       ├── Main.java
│       ├── HuffmanNode.java
│       ├── FrequencyMapBuilder.java
│       ├── HuffmanTreeBuilder.java
│       ├── HuffmanTreeSerializer.java
│       ├── HuffmanTreeDeserializer.java
│       ├── Compressor.java
│       ├── Decompressor.java
│       ├── FileHandler.java
│       └── CompressedFileData.java
│
├── data.huff
└── README.md

How It Works

Compression Flow
Read input text
Build frequency map
Construct Huffman Tree
Generate Huffman codes
Compress input into bit string
Serialize Huffman Tree
Save tree + compressed data to file

Decompression Flow
Read .huff file
Deserialize Huffman Tree
Decode compressed bit string
Restore original text


How to Run
Requirements
Java 8 or above
IntelliJ IDEA / any Java IDE

Steps
Clone the repository
git clone https://github.com/ShreyMadaan/HuffmanCompressor.git
Open project in IntelliJ IDEA
Run Main.java
Enter text when prompted


Sample Output
Enter text to compress: Shrey Mad@@n 31

Compression completed.
Compressed file saved as: data.huff

Decompressed text:
Shrey Mad@@n 31

Match with original: true


Edge Cases Handled

Empty or null input
Single unique character input
Invalid compressed data
Invalid bit sequences
Tree reconstruction safety checks


Design Decisions

Separation of Concerns
Compression logic ≠ File handling
Immutability
DTO used for compressed file data
Extensibility
Easy to add CLI, metadata, or bit-level compression


Future Enhancements

Bit-level compression using byte arrays
CLI support (compress <file>, decompress <file>)
Unit tests (JUnit)
Compression ratio analysis
Binary file format optimization

Interview Talking Points
Huffman Coding implementation
Priority Queue usage
Tree serialization & deserialization
Clean architecture principles
Edge case handling
Time & space complexity analysis

Complexity Analysis
Operation	Complexity
Tree Build	O(n log n)
Code Generation	O(n)
Compression	O(n)
Decompression	O(n)

n = number of characters


👨‍💻 Author
Shrey Madaan
B.Tech CSE (2024) | MS in Computer Science
GitHub: https://github.com/ShreyMadaan
