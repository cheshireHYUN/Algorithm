import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/** 어린왕자
 * 은하수 지도, 출발점, 도착점이 주어졌을때 어린왕자에게 필요한
 * 최소의 행성계 진입/이탈 횟수를 구하는 프로그램을 작성하라.
 * 행성계가 맞닿거나 서로 교차하는 경우는 없으며, 출발점이나 도착점이 경계인경우는 X
 * 
 * 풀이 : 최소경로같은 조건은 없으므로 그냥 크게 돌면 부딪힐일이 없음
 * 반드시 부딪히는 궤도는 딱 출발, 시작점을 내부에 갖고있는 행성들뿐임
 * 즉 O(n)으로 N개 행성의 반복문을 돌면서 두 점사이의 거리 <= r인 행성 갯수를 더하면 됨
 */

class Node {
    public Node (int x, int y){
        this.x = x;
        this.y = y;
    }
    public int x;
    public int y;
}

class Planet {
    public Planet(int x, int y, int r){
        centerX = x;
        centerY = y;
        radius = r;
    }
    public int centerX;
    public int centerY;
    public int radius;

    public boolean isNodeInPlanet(Node node1,Node node2){
        //하나만 행성안에 있다면 = 즉 서로 다른행성에 있다면 true
        boolean b1 = Math.sqrt(Math.pow(node1.x - centerX,2) + Math.pow(node1.y - centerY,2)) <= radius;
        boolean b2 = Math.sqrt(Math.pow(node2.x - centerX,2) + Math.pow(node2.y - centerY,2)) <= radius;
        return b1 != b2;
    }
}


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int planetCnt;
        Node startNode, endNode;
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<T; i++){
            st = new StringTokenizer(br.readLine());
            startNode = new Node(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
            endNode = new Node(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
            planetCnt = Integer.parseInt(br.readLine());

            int cnt = 0;
            for(int j=0; j<planetCnt; j++){
                st = new StringTokenizer(br.readLine());
                Planet planet = new Planet(Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()));
                if(planet.isNodeInPlanet(startNode, endNode)) cnt++;
            }
            sb.append(cnt).append("\n");
        }

        System.out.println(sb);

    }
}