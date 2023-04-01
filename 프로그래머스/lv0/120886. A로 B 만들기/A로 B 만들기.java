import java.util.*;
class Solution {
    public int solution(String before, String after) {
        int answer = 0;
        char[] bArr = before.toCharArray();
        char[] aArr = after.toCharArray();
        Arrays.sort(aArr);
        Arrays.sort(bArr);
        if(String.valueOf(aArr).equals(String.valueOf(bArr))) answer = 1;
        return answer;
    }
}