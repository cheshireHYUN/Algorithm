class Solution {
    public String solution(String my_string) {
        String answer = "";
        char[] arr = my_string.toCharArray();
        for(char c : arr){
            if(c >= 97) c -= 32;
            else c += 32;
            answer += c;
        }
        return answer;
    }
}