import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 막대기
 * 64cm막대기를 Xcm로 만든다.
 * (1)막대길이를 모두 더해 x보다 크면 작은걸 반갈
 * (2) 절반중 하나를 빼고 남은 막대 합이 X이상이면 빼둔걸 버린다.
 * (3) 반복
 *
 * (풀이) 반복문 돌면서 stick이 X보다 길면 2로 나눈다.
 *
 *          X=23인데 stick이 32라면, 반으로 자른 후 남은 막대가 16으로 X보다 작게 된다.
 *          즉 이 시점부터는 16+16중 버리는것이 없다. 이제 둘중 하나만(더 짧은것만) 돌릴것이므로
 *          아예 목표치를 줄여버린다. X=7이다. 이상태에서 하나남은 16은 또 잘라야하고
 *          8이 된다. 그럼 다음에 4+4가 되어 x=3이 되며, 4 하나 남은걸 또 돌린다
 *          2+2가 되고, x=1이 된다. 2를 또 반으로 자르면 1로 드디어 완성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        int stick = 64;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());
        int cnt = 0;

        while(x>0){
            if(stick > x) stick /= 2; //절반이 x보다 크면 걍 하나는 버려버림
            else{ //절반이 x보다 작다면
                cnt++;
                x -= stick; //목표stick에 하나 붙인다고 침 (어차피 작은스틱만 반복하니까)
            }
        }

        System.out.println(cnt);
    }
}