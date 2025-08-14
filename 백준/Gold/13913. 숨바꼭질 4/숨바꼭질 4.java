import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 숨바꼭질
 * - X일때 걸으면 1초후 X-1이나 X+1로 이동
 * - X일때 순간이동하면 1초후 2*X위치로 이동
 * 수빈이와 동생이 주어졌을때 수빈이가 동생을 찾는 가장 빠른시간?
 *
 * 풀이
 * bfs로 최단거리를 찾는다.
 * 경로를 저장할때 String path로 저장하려니까 시간초과 => 문자열을 매번 새로 만드니까 O(노드수x경로길이)로 불어나기 때문
 * 최적화 : parent에 이전노드를 저장해서 역추적을 통해 경로를 만든다!
 */
public class Main {
    static int[] parents = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        bfs(N,K); //동생찾을때까지 parents배열 채우기
        List<Integer> path = search(N,K); //동생위치부터 경로를 역추적

        StringBuilder sb = new StringBuilder();
        for(int i=path.size()-1; i>=0; i--) sb.append(path.get(i)).append(' ');
        System.out.println(path.size()-1);
        System.out.println(sb);
    }

    public static List<Integer> search(int start, int end) {
        List<Integer> path = new ArrayList<>();
        int index = end;
        while(true) {
            path.add(index);
            if(index == start) break;
            index = parents[index];
        }
        return path;
    }

    public static void bfs(int start, int end) {
        Arrays.fill(parents, -1);
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        parents[start] = start; //시작점엔 일단 시작점만 넣기

        while(!q.isEmpty()) {
            int currIndex = q.poll();

            if(currIndex == end) {  //도착하면 종료
                break;
            }

            //순간이동 할 경우
            if(currIndex*2<100001 && parents[currIndex*2]==-1) {
                parents[currIndex*2] = currIndex;
                q.add(currIndex*2);
            }
            //X+1로 갈 경우
            if(currIndex+1<100001 && parents[currIndex+1]==-1) {
                parents[currIndex+1] = currIndex;
                q.add(currIndex+1);
            }
            //X-1로 갈 경우
            if(currIndex-1>=0 && parents[currIndex-1]==-1) {
                parents[currIndex-1] = currIndex;
                q.add(currIndex-1);
            }
        }
    }
}
