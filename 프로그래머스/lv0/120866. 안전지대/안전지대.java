class Solution {
    public int solution(int[][] board) {
        int cnt = 0;
        //이차원배열 새로 하나 만들고, 반복 돌면서 1 근처의 0들 1로 바꿔주면서 카운트
        int[][] arr = new int[board.length][board.length];
        int[] dx = {0,1,0,-1,-1,1,-1,1};
        int[] dy = {-1,0,1,0,1,1,-1,-1};
        
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board.length; j++){
                if(board[i][j]==1){
                    // 지뢰에 대해, arr이 0이라면 1로바꿔주고 cnt
                    if(arr[i][j]==0) {
                        arr[i][j] = 1;
                        cnt++;
                    }
                    // 지뢰니까 8방향을 방향벡터 사용하여 !!0일경우!! 1로 바꾸고 cnt
                    for(int k=0; k<8; k++){
                        int nx = i+dx[k];
                        int ny = j+dy[k];
                        // nx,ny라는 인덱스가 board의 크기 벗어나면 안됨
                        if(nx>=0&&nx<board.length&&ny>=0&&ny<board.length
                           && arr[nx][ny]==0) {
                            arr[nx][ny]=1;
                            cnt++;
                        }
                    }
                }
            }
        }
        int answer = board.length*board.length-cnt;
        return answer;
    }
}