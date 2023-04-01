class Solution {
    public int solution(int i, int j, int k) {
        int answer = 0;
        for(int n=i; n<j+1; n++){
            if(Integer.toString(n).contains(Integer.toString(k))){
                int tmp = n;
            	while(tmp>0){
                    if(tmp%10==k) answer ++;
                    tmp = tmp/10;
                }
            }
        }
        return answer;
    }
}