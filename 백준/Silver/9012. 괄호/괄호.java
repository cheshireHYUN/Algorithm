import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/** 괄호
 * ()짝을 VPS라고 부른다. VPS인지 아닌지 판단해라
 * 풀이 : 스택을 이용해서 (((가 push된만큼 )))가 팝되어야함. push안됐는데 pop할라하면 안됨 주의
 */
public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T =Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            char[] arr = br.readLine().toCharArray();
            if(solution(arr)) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }
        System.out.println(sb);
    }

    private static boolean solution(char[] arr){
        Stack<Character> stack = new Stack<>();
        for(char c : arr){
            if(c=='(') stack.push(c);
            else {
                if(stack.size() == 0) return false;
                if(stack.peek() == '(') stack.pop();
                else return false;
            }
        }
        if(stack.isEmpty()) return true;
        else return false;
    }
}