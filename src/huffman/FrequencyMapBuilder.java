package huffman;

import java.util.HashMap;
import java.util.Map;

public class FrequencyMapBuilder {
    public static Map<Character,Integer> buildFreqMap(String input){
        HashMap<Character,Integer> freqMap = new HashMap<>();
        for(char c:input.toCharArray()){
            freqMap.put(c, freqMap.getOrDefault(c,0)+1);
        }
        return freqMap;
    }
}
