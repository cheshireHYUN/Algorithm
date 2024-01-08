/*
N마리 포켓몬 중에서 N/2마리를 가져간다.
포켓몬은 종류에따라 번호를 붙여 구분한다.(같은종류 포켓몬은 같은번호 가짐)

최대한 많은 종류의 포켓몬을 포함해서 N/2마리를 선택하려한다.

풀이 : 종류번호를 key, 갯수를 value로 해서 hashmap을 만든 뒤
이중에서 N/2마리를 뽑는다. key기준으로 뽑으면 되겠죠..?
*/
import java.util.*;
class Solution {
    public int solution(int[] nums) {
        int selectCnt = nums.length/2;
        
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) map.put(num, map.getOrDefault(num,0)+1);
        
        int answer = 0;
        if(map.size()>selectCnt) answer = selectCnt;
        else answer = map.size();
        
        return answer;
    }
}