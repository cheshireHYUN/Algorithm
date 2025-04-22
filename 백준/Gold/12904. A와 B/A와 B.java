import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** A와 B
 * 문자열의 뒤에 A를 추가한다
 * 문자열을 뒤집고 뒤에 B를 추가한다
 * 주어진 조건을 이용해서 S를 T로 만들 수 있는지 없는지 알아내는 프로그램을 작성하라.
 * BA -> ABB -> ABBA가 되기 떄문에 가능...
 * 그래프로 풀수있을것같은데? 근데 이제 역순인
 * S -> T를 구하면 가짓수가 2개씩 늘어나지만, T -> S를 구하면 훨씬 경우의수가 적어짐
 * 1) 문자열의 뒤에있는 A를 삭제하거나
 * 2) 문자열의 뒤에있는 B를 삭제하고 문자열을 뒤집거나
 * T의 길이가 S가 되면 종료하면 되는것!
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();
        while(T.length() > S.length()) {
            char lastChar = T.charAt(T.length()-1);
            T = T.substring(0, T.length()-1);
            if(lastChar == 'B') {
                StringBuilder sb = new StringBuilder();
                sb.append(T);
                sb.reverse();
                T = sb.toString();
            }
        }

        if(T.equals(S)) System.out.println(1);
        else System.out.println(0);

    }
}
