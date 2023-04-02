import java.util.*;
class Solution {
    public String solution(String s) {
        //MAP만들어서 값:갯수
        HashMap<Character,Integer> map = new HashMap<>();
        String answer = "";
        for(char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c,0)+1);
        }
        List<Character> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        for(char c : list){
            if(map.get(c)==1) answer+=c;
        }
        return answer;
    }
}