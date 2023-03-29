class Solution {
    public int solution(int[][] dots) {
        //음수일수 있으니 절댓값 (Math.abs())
        //x중에서 가장 큰값 - x중에서 가장 작은 값 => x의 길이
        //y중에서 가장 큰값 - y중에서 가장 작은 값 => y의 길이
        int xmax=Integer.MIN_VALUE, xmin=Integer.MAX_VALUE, ymax=Integer.MIN_VALUE, ymin=Integer.MAX_VALUE;
        for(int i=0; i<4; i++){
            xmax = Math.max(xmax,dots[i][0]);
            xmin = Math.min(xmin,dots[i][0]);
            ymax = Math.max(ymax,dots[i][1]);
            ymin = Math.min(ymin,dots[i][1]);
        }
        int answer = Math.abs(xmax-xmin)*Math.abs(ymax-ymin);
        return answer;
    }
}