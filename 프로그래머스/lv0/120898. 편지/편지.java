class Solution {
    public int solution(String message) {
        //글자 한자당 2cm, message의 최소 가로길이 (공백도 문자)
        char[] arr = message.toCharArray();
        int answer = arr.length*2;
        return answer;
    }
}