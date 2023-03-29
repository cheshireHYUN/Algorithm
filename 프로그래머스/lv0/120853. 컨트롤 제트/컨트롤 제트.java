class Solution {
    public int solution(String s) {
        //숫자더하기 할건데 Z가 나오면 바로전에 더한거 빼는거임.
        int answer = 0;
        String[] strArr = s.split(" ");
        for(int i=0; i<strArr.length; i++){
            if(strArr[i].equals("Z")){
                answer -= Integer.parseInt(strArr[i-1]);
            }
            else answer += Integer.parseInt(strArr[i]);
        }
        
        return answer;
    }
}