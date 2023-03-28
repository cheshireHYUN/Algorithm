class Solution {
    public int solution(int n) {
        int answer = 1;
        int i = 1;
        while(n >= i*answer){
            i *= answer; // answer=1, 1 | answer=2, 1*2 | answer=3 1*2*3
            answer++;
        }
        return answer-1; //끝났는데도 answer++;은 실행되니까 하나빼주기
    }
}