import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/** 틱택토
 * 선발주자가 X, 후발주자가 O를 놓으며 (.은 빈칸)
 * 언제든지 한사람의 말이 가로/세로/대각선을 세칸 잇거나 가득차면 종료
 * 상태가 주어졌을때 게임에서 발생할수있는 최종상태인지 판별하라.
 * 가능할경우 valid, 불가능할경우 invalid를 출력한다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true){
            String line = br.readLine();
            if(line.equals("end")) {
                System.out.println(sb);
                break;
            }
            sb.append(getResult(line)).append("\n");
        }
    }

    /** 최종상태 판별 메소드
     * (1)X갯수와 O갯수가 n(X)=n(O) 또는 n(X)=n(O)+1
     *  => false이면 invalid, true이면 (2)번
     * (2)가로세로대각선에서 이긴사람 있나 검사 => 둘다이겼다고 나오면(t) 바로 false 리턴때리기
     *  => false이면 (3) true이면 (4)
     * (3).이 없다면 즉 꽉찼다면 valid, 안찼다면 invalid
     * (4)X가이겼을땐 n(X)=N(O)+1이고 O가이겼을땐 n(O)=n(X)
     *  => true이면 valid false이면 invalid
     */
    private static String getResult(String line) {
        char[][] arr = new char[3][3];
        char[] lineArr = line.toCharArray();
        int l = 0, Xcnt=0, Ocnt=0, nullCnt=0;

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(lineArr[l] == 'X') Xcnt++;
                if(lineArr[l] == 'O') Ocnt++;
                if(lineArr[l] == '.') nullCnt++;
                arr[i][j] = lineArr[l++];
            }
        }

        if(!cntCompare(Xcnt,Ocnt)) return "invalid";
        else {
            char winner = getWinner(arr);
            switch(winner){
                case 'O' : {
                    if(Ocnt == Xcnt) return "valid";
                    break;
                }
                case 'X' : {
                    if(Xcnt == Ocnt+1) return "valid";
                    break;
                }
                case 'T' : {
                    return "invalid";
                }
                default: {
                    if(nullCnt == 0) return "valid";
                    else return "invalid";
                }
            }

        }

        return "invalid";
    }

    private static char getWinner(char[][] arr) {
        char result = '.';
        for(int i=0; i<3; i++){
            if(arr[i][0] == arr[i][1] && arr[i][1] == arr[i][2] && arr[i][0] != '.') {
                if(result == '.' || result == arr[i][0]) result = arr[i][0];
                else return 'T';
            }
            else if(arr[0][i] == arr[1][i] && arr[1][i] == arr[2][i] && arr[0][i] != '.') {
                if(result == '.' || result == arr[0][i]) result = arr[0][i];
                else return 'T';
            }
        }
        if(arr[0][0]==arr[1][1] && arr[1][1]==arr[2][2] && arr[0][0] != '.') {
            if(result == '.' || result == arr[0][0]) result = arr[0][0];
            else return 'T';
        }
        if(arr[0][2]==arr[1][1] && arr[1][1]==arr[2][0] && arr[0][2] != '.') {
            if(result == '.' || result == arr[0][2]) result = arr[0][2];
            else return 'T';
        }

        return result;
    }

    private static boolean cntCompare(int xcnt, int ocnt) {
        if(xcnt == ocnt) return true;
        else if(xcnt == ocnt+1) return true;
        else return false;
    }

}