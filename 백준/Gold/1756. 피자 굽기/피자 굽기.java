import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 피자굽기
 * N개의 피자가 오븐에 모두 들어간 뒤, 맨 위의 피자가 얼마나 깊이 들어있는지 알아내자.
 *
 * 풀이
 * 일단 완탐처럼 하나씩 넣으면? 오픈깊이가 30억, 반죽이 30억이니까 각 반죽 X 30! 이니까 터진다.
 * 따라서 최대한 이중포문을 피하려면 다음 아이디어를 활용한다.
 * (1) 예를들어 위에서부터 5 6 7 8의 너비를 가질 경우, 5 5 5 5와 같은 누적으로 생각해야됨. 어차피 아래가 넓어져도 결국 위에서 멈춰버리기 때문
 * (2) 피자가 들어갈 수 있는 가장 깊은 위치를 이분탐색으로 찾는다(어차피 누적최소값이라 뒤로갈수록 작아지므로 이분탐색이 가능)
 * (3) 예를들어 피자 지름이 3일경우, 0~D까지 높이중 3과 같거나 큰 가장 깊은위치의 인덱스를 찾는다. 이때 index가 4일경우
 * (4) 두번째 피자 지름이 2일 경우, 0~3까지 높이중 2와 같거나 큰 가장 깊은위치의 인덱스를 찾는다. index=3으로 업데이트
 * 이렇게 반복하면서 모든 피자가 들어갈 수 있는지 찾고, +1 해서 리턴한다.
 * 즉 "각 피자가 들어갈수있는 가장 깊은 위치를 이분탐색해서 결과가 나오면 가능, 안나오면 불가능인것"
 * 처음 내가 풀었던 매개변수 탐색 형식은 maxHeight를 정해놓는거라 이중포문이 들어가서 시간복잡도 터졌던거네~~!!
 *
 * 그럼 좀 더 구체화 해보자면,
 * 1. 최소누적값으로 depth정보를 변환한다.
 *      => i=0부터 탐색하면서 min값을 저장하고, if(depth[i]>min) depth[i]=min, else min=depth[i];를 반복
 * 2. pizza배열을 순회하면서 이분탐색을 진행, 피자를 넣을 수 있는 가장 낮은곳(인덱스 큰곳)"을 찾는다. 처음 index=D
 *      => min=0 ~ max=index를 이분탐색하며 if(depth[mid] >= pizza) index=i, min=mid+1 // else max=mid-1
 * 3. 이분탐색 결과가 모두 나올 경우, 마지막 index값+1이 정답(한번이라도 이분탐색 결과가 안나오면 0)
 */
public class Main {
    static int D,N;
    static int[] depth, pizza;
    public static void main(String[] args) throws IOException {
        setInput();
        int index = D-1;
        for(int i=0; i<N; i++) {
            int maxLow = binarySearch(pizza[i], index);
            if(maxLow == Integer.MAX_VALUE) {
                System.out.println(0);
                return;
            }

            index = maxLow-1;
        }
        System.out.println(index+2);
    }

    private static int binarySearch(int piz, int index) {
        int min = 0, max = index, result = Integer.MAX_VALUE;
        while(min<=max) {
            int mid = (min+max)/2;
            if(depth[mid] >= piz) {
                result = mid;
                min = mid+1;
            } else max = mid-1;
        }

        return result;
    }

    private static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken()); //오븐의 깊이
        N = Integer.parseInt(st.nextToken()); //피자반죽 갯수

        depth = new int[D];
        st = new StringTokenizer(br.readLine());
        int pre = Integer.MAX_VALUE;
        for (int i = 0; i < D; i++)  {
            depth[i] = Integer.parseInt(st.nextToken());
            if(depth[i]>=pre) depth[i] = pre;
            else pre = depth[i];
        }

        pizza = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)  pizza[i] = Integer.parseInt(st.nextToken());
    }
}
