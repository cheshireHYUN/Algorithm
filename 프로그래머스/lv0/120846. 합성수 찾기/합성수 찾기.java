class Solution {
    public int solution(int n) {
        //에라토스테네스의 체
        int[] arr = new int[n+1];
        int cnt=0;
        for(int i=2; i<=n; i++){
            if(arr[i]==0){
                cnt++;
                for(int j=i; j<=n; j=i+j) {
                    arr[j]=1;
                }
            }
        }
        cnt = n-cnt-1; //[0]일때 빼줘야함
        return cnt;
    }
}