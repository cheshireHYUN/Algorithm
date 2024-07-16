import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 벡터매칭
 * 평면에 N개 점이 있고, 이 집합을 P라고 한다.
 * P의 내부에서 벡터매칭, 즉 두점을 이어 벡터를 만든다. 모든점이 쓰여야함
 * 즉 벡터는 N/2개가 나올것임. 이때 P의 벡터매칭들의 합의 길이의 최솟값을 구하라.
 *
 * 풀이 : 벡터를 매칭시키면 v1 = (x2-x1, y2-y1)이고 v2 = (x4-x3, y4-y3)이다.
 * 그렇다면 벡터합은? v1+v2 = (x2+x4-x1-x2, y2+y4-y1-y2)이다. 두 도착점은 더하고, 두 출발점은 뺸다.
 * 길이를 정수화하면, ||v|| = 루트 {(x2+x4-x1-x2)^2+(y2+y4-y1-y2)^2}이 되는것이다.
 * 즉, 출발점 또는 도착점의 두합을 구하고, 그에 맞춰 벡터합을 구한 뒤 가장 작은 벡터합 경우를 찾으면 된다.
 * N이 최대 20이므로 20C10 = 184756이다. 테스트케이스 * 184756
 */

public class Main {
    private static class Node {
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int x;
        public int y;
    }
    private static double minSum = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++){
            minSum = Integer.MAX_VALUE;
            int N = Integer.parseInt(br.readLine()); //점의 갯수
            Node[] nodes = new Node[N]; //각 점의 위치
            for(int j=0; j<N; j++){
                st = new StringTokenizer(br.readLine());
                nodes[j] = new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            }
            //nodes중에 절반만큼만 조합을 구한다.
            plusIndexArr = new boolean[N];
            getCombination(nodes, 0, 0);
            sb.append(minSum).append("\n");
        }
        System.out.println(sb);
    }

    private static boolean[] plusIndexArr;
    private static void getCombination(Node[] nodes, int cnt, int start) {
        if(cnt == nodes.length/2) {
            //해당 조합에서 벡터합을 구한다.
//            System.out.println(Arrays.toString(plusIndexArr));
            minSum = Math.min(getVectorSum(nodes), minSum);
            return;
        }

        for(int i=start; i<nodes.length; i++) {
//            indexArr[cnt] = i;
            plusIndexArr[i] = true;
            getCombination(nodes, cnt+1, i+1);
            plusIndexArr[i] = false;
        }
    }

    private static double getVectorSum(Node[] nodes) {
        //Math.sqrt(Math.pow((x3+x4-x1-x2),2)+Math.pow((y3+4+y1+y2),2))
        int plusX=0, minusX=0, plusY=0, minusY=0;
        for(int i=0;i<nodes.length;i++){
            if(plusIndexArr[i]){
                //즉, 도착점이면 더한다
                plusX += nodes[i].x;
                plusY += nodes[i].y;
            } else{
                //즉, 출발점이면 뺀다
                minusX -= nodes[i].x;
                minusY -= nodes[i].y;
            }
        }
        return Math.sqrt(Math.pow(plusX+minusX,2)+Math.pow(plusY+minusY,2));
    }
}