import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/** 큐
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            switch (str) {
                case "push" : {
                    int value = Integer.parseInt(st.nextToken());
                    pushValue = value;
                    push(value);
                    break;
                }
                case "front" : {
                    front();
                    break;
                }
                case "back" : {
                    back();
                    break;
                }
                case "size" : {
                    size();
                    break;
                }
                case "empty" : {
                    empty();
                    break;
                }
                case "pop" : {
                    pop();
                    break;
                }
            }
        }
        System.out.println(sb);
    }

    static StringBuilder sb = new StringBuilder();
    static Queue<Integer> q = new ArrayDeque<>();
    static int pushValue = 0;
    private static void push(int value){
        q.offer(value);
    }

    private static void pop(){
        if(q.size() == 0) sb.append(-1).append("\n");
        else sb.append(q.poll()).append("\n");
    }

    private static void size(){
        sb.append(q.size()).append("\n");
    }

    private static void empty(){
        if(q.size() == 0) sb.append(1).append("\n");
        else sb.append(0).append("\n");
    }

    private static void front(){
        if(q.size() == 0) sb.append(-1).append("\n");
        else sb.append(q.peek()).append("\n");
    }

    private static void back(){
        if(q.size() == 0) sb.append(-1).append("\n");
        else sb.append(pushValue).append("\n");
    }
}