class Solution {
    public int solution(int n) {
        int answer = 2;
        int tmp =1;
        while(n>=tmp){
            if(tmp*tmp ==n) answer=1;
            tmp++;
        }
        return answer;
    }
}