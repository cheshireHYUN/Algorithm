import java.util.*;
class Solution {
    public int solution(String my_string) {
        int answer = 0;
        my_string += "a";
        char[] arr = my_string.toCharArray();
        String str = "";
        
        for(int i=0; i<arr.length-1; i++){
            if(Character.isAlphabetic(arr[i+1]) && Character.isDigit(arr[i])){
                //현재 숫자이고 다음순번이 영어인경우
                str+=arr[i];
                answer += Integer.parseInt(str);
                str = "";
            }
            // 현재 숫자이고 다음순번이 영어가 아닌경우
            else if(!Character.isAlphabetic(arr[i+1]) && Character.isDigit(arr[i])) str+=arr[i];
        }
        return answer;
    }
}