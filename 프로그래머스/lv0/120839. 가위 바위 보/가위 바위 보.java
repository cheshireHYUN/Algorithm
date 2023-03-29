class Solution {
    public String solution(String rsp) {
        // 가위 2, 바위 0, 보 5
        String answer = "";
        char[] arr = rsp.toCharArray();
        for(char c : arr){
            if(c-48 == 2) answer += "0";
            else if(c-48==0) answer += "5";
            else if(c-48 ==5)answer += "2";
        }
        return answer;
    }
}