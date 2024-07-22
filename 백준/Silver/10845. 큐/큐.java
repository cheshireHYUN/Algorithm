import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/** 큐
 * 정수를 저장하는 큐를 구현한 다음 입력으로 주어지는 명령을 처리하는 프로그램을 작성
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> q = new ArrayDeque<>();
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int last = 0;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            switch (str){
                case "push" : {
                    last = Integer.parseInt(st.nextToken());
                    q.offer(last);
                    break;
                }
                case "pop" : {
                    sb.append(q.isEmpty()? -1 :q.poll()).append("\n");
                    break;
                }
                case "size" : {
                    sb.append(q.size()).append("\n");
                    break;
                }
                case "empty" : {
                    sb.append(q.isEmpty()? 1 : 0).append("\n");
                    break;
                }
                case "front" : {
                    sb.append(q.isEmpty()? -1 : q.peek()).append("\n");
                    break;
                }
                case "back" : {
                    sb.append(q.isEmpty()? -1 : last).append("\n");
                    break;
                }
            }
        }
        System.out.println(sb);
    }
}