import java.util.*;
class Solution {
    public List<Integer> solution(String my_string) {
        List<Integer> list = new ArrayList<>();
        for(char c : my_string.toCharArray()){
            if(Character.isDigit(c)) list.add(Character.getNumericValue(c));
        }
        Collections.sort(list);
        return list;
    }
}