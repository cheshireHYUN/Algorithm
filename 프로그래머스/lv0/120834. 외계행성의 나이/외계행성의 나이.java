class Solution {
    public String solution(int age) {
        String answer = "";
        String tmp = Integer.toString(age);
        char[] arr = tmp.toCharArray();
        for(char i : arr){
            answer += (char)(i+49); //1=>49, a=>97
        }
        return answer;
    }
}