import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/** 좌표정렬하기 2
 * 2차원 평면에 점 N개가 있을때 좌표를 y증가방향 순으로 y가같으면 x증가방향순으로 정렬한다음 출력하라.
 * class로 선언해둔 뒤 compareTo 메소드를 통해서... this-o가 오름차순인거 잊지말기
 */
public class Main {
    private static class Node implements Comparable<Node> {
        public int x;
        public int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Node o){
            if(this.y == o.y) return this.x-o.x;
            return this.y - o.y;
        }

        @Override
        public String toString() {
            return x+" "+y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<Node> list = new ArrayList<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            list.add(new Node(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()))
            );
        }
        Collections.sort(list);
        for(Node n : list) System.out.println(n);
    }}