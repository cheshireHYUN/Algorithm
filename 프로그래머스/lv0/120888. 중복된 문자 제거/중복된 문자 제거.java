class Solution {
    public String solution(String my_string) {
        String answer = "";
        char[] arr = my_string.toCharArray();
        for(char c : arr){
            if(!answer.contains(String.valueOf(c))) answer+=c;
        }
        return answer;
    }
}