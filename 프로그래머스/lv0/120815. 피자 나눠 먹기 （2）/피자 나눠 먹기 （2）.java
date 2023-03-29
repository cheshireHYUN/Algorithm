class Solution {
    public int solution(int n) {
        int answer = 0;
        //6의배수가 n의 배수여야함 즉, 6의 배수와 n의 최소공배수
        int k = 1;
        while(true){
            int tmp = 6*k;
            if(tmp%n == 0){
                answer = k;
                break;
            }
            k++;
        }
        
        return answer;
    }
}