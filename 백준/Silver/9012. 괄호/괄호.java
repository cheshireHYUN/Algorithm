import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Stack;

/** 괄호
 * 괄호가 올바르게 구성된걸 VPS라고 하고 x가 vps라면 (x)도 VPS다. 결국 잘 닫히면 VPS다.
 * 입력이 VPS인지 YES No출력하라
 * 풀이 : 괄호인거부터 감이오죠 스택쓰면서 괄호를 잘 확인하는 문제
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<T; i++){
            Stack<String> stack = new Stack<>();
            String str = br.readLine();
            int popCnt = 0;
            for(int j=0; j<str.length(); j++){
                String curr = str.substring(j,j+1);
                if(curr.equals("(")) stack.add(curr);
                else if(curr.equals(")") && !stack.isEmpty()) stack.pop();
                else {
                    stack.add(curr);
                    break;
                }
            }
            if(stack.isEmpty()) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }
        System.out.println(sb);
    }
}