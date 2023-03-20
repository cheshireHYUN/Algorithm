class Solution {
    public double solution(int[] numbers) {
        double answer = 0;
        double total = 0;
        for(double b : numbers){
            total += b;
        }
        answer = total/numbers.length;
        return answer;
    }
}