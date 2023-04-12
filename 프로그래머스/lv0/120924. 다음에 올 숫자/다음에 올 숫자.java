class Solution {
    public int solution(int[] common) {
        int answer = 0;
        if(common[2]-common[1] == common[1]-common[0]){
            //등차수열
            answer = common[common.length-1]+(common[2]-common[1]);
        } else{
            //등비수열
            answer = common[common.length-1]*(common[2]/common[1]);
        }
        return answer;
    }
}