class Solution {
    public int solution(int[] numbers, int k) {
        int answer = 0;
        int tmp = (k-1)*2; //tmp번 던지는것임
        answer = numbers[tmp%numbers.length];
        return answer;
    }
}