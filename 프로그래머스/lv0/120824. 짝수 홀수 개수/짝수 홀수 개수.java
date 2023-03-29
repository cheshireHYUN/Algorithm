class Solution {
    public int[] solution(int[] num_list) {
        int a=0, b=0;
        for(int k : num_list) {
            if(k%2==0) a++;
            else b++;
        }
        int[] answer = {a,b};
        return answer;
    }
}