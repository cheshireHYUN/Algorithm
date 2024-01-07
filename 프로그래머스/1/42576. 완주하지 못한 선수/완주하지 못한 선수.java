/*
한명빼고 마라톤 완주함
paricipant : 참여한 선수의 이름이 담긴배열
completion : 완주한 선수의 이름이 담긴 배열
(return) 완주하지 못한 선수의 이름을 return

참가자중에는 동명이인이 있을 수 있음

*/
import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> checkingMap = new HashMap<>();
        //완주자 목록을 저장하는 map
        for(String s : completion){
            int cnt = checkingMap.getOrDefault(s, 0);
            checkingMap.put(s,cnt+1);
        }
        
        String answer = "";
        //완주자map에 존재하지 않는경우 return
        for(String s : participant){
            if(null == checkingMap.get(s)) {
                answer = s;
                break;
            } else {
                if(checkingMap.get(s) == 0) {
                    answer = s;
                    break;
                }
                checkingMap.put(s,checkingMap.get(s)-1);
            }
        }
        return answer;
    }
}