import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/** 쇠막대기
 * 쇠막대기를 겹쳐놓고 레이저를 발사해 한번에 자른다.
 * 쇠막대기 조건 : 자신보다 긴 쇠막대 위에만 놓을수있다. 이때
 * 완전히 포함되도록 놓되 끝점은 겹치면 안된다.
 * 각 쇠막대를 자르는 레이저는 적어도 하나 존재한다.
 * 레이저는 어떤 쇠막대기의 양 끝점과도 겹치지 않는다.
 *
 * 레이저는 '()'로 표현한다.
 * 쇠막대기는 '('와 ')'로 표현한다,
 * 쇠막대기와 레이저 정보가 주어질때 잘려진 쇠막대의 조각의 총 개수?
 *
 * 풀이 : 스택에 (면 넣고 )면 빼는 방식으로 쌓는다
 * 포인트는 ()가 나오면 스택 사이즈를 누적한다(잘릴 막대기 갯수)
 * ()가 아닌 (와 )가 짝지어 pop된다면 1을 더한다.
 * 3 + 3 + 1 + 3 + 1 + 2 + 1 + 1 + 1 + 1 = 17
 * 따라서 반복을 돌면서 Stack에 누적하는데, i와 i+1번째가 ()면 사이즈 누적,
 * (와 )가 따로 나오면 +1
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();
        int answer = 0;
        for(int i=0; i<arr.length; i++){
            if(arr[i] == '(') {
                if(arr[i+1] == ')') {
                    answer += stack.size();
                    i++;
                }
                else stack.push(arr[i]);
            }
            else if(arr[i] == ')') {
                stack.pop();
                answer++;
            }
        }
        System.out.println(answer);
    }
}