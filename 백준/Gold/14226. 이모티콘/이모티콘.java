import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/** 이모티콘
 * 영선이는 효빈이에게 스마일 이모티콘을 S개 보낸다.
 * 이미 화면에 이모티콘 1개를 입력했고, 3가지 연산을 통해 이모티콘을 S개 만들어보려고 한다.
 * 1. 화면의 이모티콘을 모두 복사해서 클립보드에 저장한다.
 * 2. 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
 * 3. 화면에 있는 이모티콘중 하나를 삭제한다.
 * 모든 연산은 1초가 걸리며, 클립보드에 이모티콘을 복사하면 원래 있던 클립보드 내용은 덮어써진다.
 * 화면에 붙여넣기를 하면 클립보드에 있는 이모티콘의 갯수가 화면에 추가된다.
 * S개의 이모티콘을 화면에 만드는데 걸리는 시간의 최솟값?
 *
 * 풀이
 * DP를 고려해봤으나, 너무도 다양한 수가 있어 불가능하다고 판단된다.
 * 따라서 BFS를 이용해서 최단경로를 탐색한다.
 *
 * 큐를 돌때, 현재 큐에서 진행할 수 있는 내용들을 진행한 뒤 다시 큐에 넣는다
 * 즉, (클립보드 이모티콘 갯수, 현재까지 화면에 뜬 이모티콘 갯수, 시간)를 노드로 해서 진행한다.
 * (1) (0,1,0)이 셋팅되어있으니, 이를 통해 만들 수 있는 경우의 수를 찾는다
 *      맨 초기값은 위에서 복사+붙여넣기를 한번 진행한 값인 (1,2,2)
*  (2) (1,2,2)에서 할 수 있는 행동 3가지를 진행한 뒤, 큐에 넣는다.
 *      복사 -> (1+1,2,2+1)
 *      붙여넣기 -> (1,2+1,2+1) [주의할점은 클립보드가 비어있으면(=0) 붙여넣기가 불가능함.]
 *      빼기 -> (1,2-1,2+1)
 *
 * 또한 중복을 방지하기 위한 체크배열이 필요한데... BFS라서 먼저 도착하면 걔가 빠른거임
 * 그니까 check[i]만들어두고 값 이미 있으면 중단하려고 했으나, 이렇게하면 뒤에 최단거리가 될 수 있는
 * 후보군들이 사라져버림! 따라서 check[클립보드에 있는 갯수][화면에 보여지는갯수]로 설정해서 체크해야함~!
 */
public class Main {
    static int S;
    static boolean check[][];
    static class Node {
        public int clipBoardCnt;
        public int emoticonCnt;
        public int time;

        public Node(int clipBoardCnt, int emoticonCnt, int time) {
            this.clipBoardCnt = clipBoardCnt;
            this.emoticonCnt = emoticonCnt;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());
        check = new boolean[10000][10000];
        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(1,2,2));
        while(!q.isEmpty()) {
            Node curr = q.poll();
            if(curr.clipBoardCnt<0 || curr.emoticonCnt<0 ||
                    check[curr.clipBoardCnt][curr.emoticonCnt]) continue;
            if(curr.emoticonCnt == S) {
                return curr.time;
            }
            check[curr.clipBoardCnt][curr.emoticonCnt] = true;

            if(curr.clipBoardCnt != 0) q.add(ctrlV(curr));
            q.add(ctrlC(curr));
            q.add(minus(curr));
        }

        return -1;
    }

    public static Node ctrlC(Node node) { //복사 -> 클립보드 갯수를 화면의 갯수로 바꾸고 시간+1
        Node newNode = new Node(node.clipBoardCnt, node.emoticonCnt, node.time);
        newNode.clipBoardCnt = newNode.emoticonCnt;
        newNode.time++;
        return newNode;
    }

    public static Node ctrlV(Node node) { //붙여넣기 -> 클립보드 갯수를 화면갯수에 더해주고 시간+1
        Node newNode = new Node(node.clipBoardCnt, node.emoticonCnt, node.time);
        newNode.emoticonCnt += newNode.clipBoardCnt;
        newNode.time++;
        return newNode;
    }

    public static Node minus(Node node) { //빼기 -> 화면 갯수 -1, 시간+1
        Node newNode = new Node(node.clipBoardCnt, node.emoticonCnt, node.time);
        newNode.emoticonCnt--;
        newNode.time++;
        return newNode;
    }
}