import java.util.*;
class Solution {
    public int solution(String s) {
        //숫자더하기 할건데 Z가 나오면 바로전에 더한거 빼는거임.
        int answer = 0;
        String[] strArr = s.split(" ");
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<strArr.length; i++){
            if(strArr[i].equals("Z")){
                stack.pop();
            }
            else stack.push(Integer.parseInt(strArr[i]));
        }
        
        for(int i : stack){
            answer += i;
        }
        return answer;
    }
}