package huffman;

import java.util.Map;
import java.util.TreeMap;

/** Builds a sorted frequency table; sorting makes archive generation reproducible. */
public final class FrequencyMapBuilder {
    private FrequencyMapBuilder() { }
    public static Map<Character, Integer> buildFreqMap(String input) {
        if (input == null) throw new IllegalArgumentException("Input cannot be null");
        Map<Character, Integer> frequencies = new TreeMap<>();
        for (char symbol : input.toCharArray()) frequencies.merge(symbol, 1, Math::addExact);
        return frequencies;
    }
}
