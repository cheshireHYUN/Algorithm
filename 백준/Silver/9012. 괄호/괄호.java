import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/** 괄호
 * stack을 이용하여 (일때 push )일때 pop한다. push할게없는데 pop하려고 하면 NO
 * 맨 마지막에 stack이 비어있지 않아도 NO
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<T; i++){
            char[] arr = br.readLine().toCharArray();
            Stack<Character> stack = new Stack<>();
            boolean flag = true;
            for(char c : arr){
                if(c=='(') stack.push(c);
                else {
                    if(stack.isEmpty()) {
                        flag = false;
                        break;
                    }
                    stack.pop();
                }
            }
            if(!flag) sb.append("NO").append("\n");
            else {
                if(stack.isEmpty()) sb.append("YES").append("\n");
                else sb.append("NO").append("\n");
            }
        }

        System.out.println(sb);

    }
}