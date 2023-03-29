class Solution {
    public int[] solution(String[] keyinput, int[] board) {
        //board에서 가로세로 최대크기 구해서 key옮겼을떄 MIN(최대크기,옮긴좌표)
        int xBoard = board[0]/2;
        int yBoard = board[1]/2;
        int[] answer = {0,0};
        for(String str : keyinput){
            if(str.equals("right")) answer[0] = Math.min(answer[0]+1, xBoard);
            else if(str.equals("left")) answer[0] = Math.max(answer[0]-1, xBoard*(-1));
            else if(str.equals("up")) answer[1] = Math.min(answer[1]+1, yBoard);
            else if(str.equals("down")) answer[1] = Math.max(answer[1]-1, yBoard*(-1));
        }
        return answer;
    }
}