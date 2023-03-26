class Solution {
    public int solution(int hp) {
        int big=5, mid=3, small=1;
        int answer = 0;
        answer = hp/big;
        hp %= big;
        answer += hp/mid;
        hp %= mid;
        answer += hp/small;
        return answer;
    }
}