import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 큐
 */
public class Main {
    static Queue<Integer> q = new ArrayDeque<>();
    static int pre;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch (command) {
                case "push" : {
                    int value = Integer.parseInt(st.nextToken());
                    push(value);
                    break;
                }
                case "pop" : {
                    sb.append(pop()).append("\n");
                    break;
                }
                case "size" : {
                    sb.append(size()).append("\n");
                    break;
                }
                case "empty" : {
                    sb.append(empty()).append("\n");
                    break;
                }
                case "front" : {
                    sb.append(front()).append("\n");
                    break;
                }
                case "back" : {
                    sb.append(back()).append("\n");
                    break;
                }
            }
        }
        System.out.println(sb);
    }
    private static void push(int value){
        pre = value;
        q.add(value);
    }
    private static int pop(){
        if(q.isEmpty()) return -1;
        else return q.poll();
    }
    private static int size(){
        return q.size();
    }
    private static int empty(){
        return q.isEmpty() ? 1:0;
    }
    private static int front(){
        if(q.isEmpty()) return -1;
        return q.peek();
    }
    private static int back(){
        if(q.isEmpty()) return -1;
        return pre;
    }
}