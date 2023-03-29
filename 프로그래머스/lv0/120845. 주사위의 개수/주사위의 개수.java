class Solution {
    public int solution(int[] box, int n) {
        int answer = 1;
        for(int b : box) answer = answer * (b/n);
        return answer;
    }

}