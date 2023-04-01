class Solution {
    public int solution(int n) {
        int answer = 2;
        if(n%Math.sqrt(n)==0) answer=1;
        return answer;
    }
}