class Solution {
    public int[] solution(int n) {
        int al = n/2;
        if(n%2 != 0) al = n/2+1;
        
        int[] answer = new int[al];
        answer[0]=1;
        for(int i=1; i<al; i++){
            answer[i] = 2*i+1;
        }        
        return answer;
    }
}