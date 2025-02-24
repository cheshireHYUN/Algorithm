import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 퍼즐
 * 3x3 퍼즐에 수가 한칸빼고 전부 채워져있다.
 * 어떤 수와 인접한 네칸중 한칸이 비어있으면 수를 빈칸으로 이동시킬 수 있다.
 * 최소의 이동으로 오른쪽 아래 가장 끝칸이 비워진 상태를 만들자.
 * 최소 이동횟수를 출력하며, 불가능하면 -1을 출력한다.
 *
 * 풀이
 * 힌트보니까 그래프, bfs라고함
 * 그래프라고 생각하면 빈칸이 오른쪽 아래를 목적지로 떠나는 여행이네?? 와 대박
 * 근데 중요한건 123 456 78도 순서대로 정리되게 해야함...
 * 0이 목적지 도착했을때 체크하면 되지 않을까.
 * 근데 큐가 무한루프 안돌게 하려면, 겹치는 경로에 대해서는 방문처리를 해줘야함.
 * 방문처리의 기준은 현재 배열의 순열 인데 생각해보니 그냥 일자로 toString해서 Set에 저장하면 되지 않을까.
 * 그럼 단순히 0을 옮기면서 탐색할 뿐만 아니라 그때그떄의 그래프 순열도 복사해서 저장해줘야함.
 * => 아예 모양을 String으로만 보관하면 간단할 것 같음!
 * 방향벡터는 갈수있는 인덱스를 계산해서 String에서 교환처리 해주면 된다.
 * 
 * 123456789 이렇게 있다고 할때(실제 인덱스는 여기서 다 -1)
 * 1번 >> 2,4 이동가능
 * 2번 >> 1,3,5 이동가능
 * 3번 >> 2,6 이동가능
 * 4번 >> 1,5,7 이동가능
 * 5번 >> 2.4.6.8 이동가능
 * 6번 >> 3,5,9 이동가능
 * 7번 >> 4,8 이동가능
 * 8번 >> 5,7,9 이동가능
 * 9번 >> 6,8 이동가능
 */
public class Main {
    public static int[][] direction = new int[][]{{1,3},{0,2,4},{1,5},{0,4,6},{1,3,5,7},{2,4,8},{3,7},{4,6,8},{5,7}};
    static class Data {
        public int zeroIndex;
        public String path;
        public Data(int zeroIndex, String path) {
            this.zeroIndex = zeroIndex;
            this.path = path;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int start = -1;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<3; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                int curr = Integer.parseInt(st.nextToken());
                sb.append(curr);
                if(curr == 0) start = sb.length()-1;
            }
        }

        System.out.println(bfs(new Data(start, sb.toString())));
    }

    private static int bfs(Data data) {
        if(validation(data.path)) return 0;

        Set<String> visitedSet = new HashSet<>();
        Queue<Data> q = new ArrayDeque<>();
        q.offer(data);
        visitedSet.add(data.path);

        int level = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            level++;
            for(int i=0; i<size; i++) {
                Data curr = q.poll();
                for(int k=0; k<direction[curr.zeroIndex].length; k++) {
                    //Data의 zeroIndex번째 문자와 direction[curr.zeroIndex][k]번째 문제를 교환한 Data 생성
                    //그럼 zeroIndex는 direction[curr.zeroIndex][k]가 되고, 내부 path는 두 순서를 변경한 값이 됨
                    String newPath = exchange(curr.path, curr.zeroIndex, direction[curr.zeroIndex][k]);
                    Data next = new Data(direction[curr.zeroIndex][k], newPath);

                    if(visitedSet.contains(next.path)) continue;
                    if(validation(next.path)) return level;
                    q.offer(next);
                    visitedSet.add(next.path);
                }
            }
        }
        return -1;
    }

    private static String exchange(String path, int index1, int index2) {
        char[] arr = path.toCharArray();
        char char1 = arr[index1];
        char char2 = arr[index2];

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<arr.length; i++) {
            if(i==index1) sb.append(char2);
            else if(i==index2) sb.append(char1);
            else sb.append(arr[i]);
        }

        return sb.toString();
    }

    private static boolean validation(String path) {
        if(path.equals("123456780")) return true;
        return false;
    }

}