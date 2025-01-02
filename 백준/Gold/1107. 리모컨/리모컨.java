import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 리모컨
 * 리모컨 보튼이 0-9까지 있고, +와-가 있다.
 * +누르면 현재채널+1, -누르면 현재채널-1, 0에서-누르면 안변하고, 채널은 무한대로있다.
 * 지금 이동하려는 채널이 N이고, 어떤버튼이 고장났는지 주어졌을때
 * M으로 이동하기 위해 최소 몇번 버튼을 눌러야 하는지 구하라.
 * 현재 채널은 100번이다.
 *
 * 입력
 * 0<=이동하려는 채널 N<=50만
 * 0<=고장난 버튼의 갯수 M<=10
 *
 * 풀이
 * 목표가 100일땐 0을 리턴/ +,-로만 가도 되는 목표면 그 갯수/ 차이가 크면 숫자만든후 100과의 차를 구함.
 *
 * 타겟번호를 어떻게 구할 수 있을까??
 *      => 가장 효울적인 방법을 찾아보려 했으나.. 정답은 브루트포스였다
 *      50만을 만드려고 할때 0~9가 모두 고장나있다면 50만-100 = 499900번의 연타가 필요함.
 *      그러나 0~5번만 고장나있다면 666666을 먼저 만든 후, 500000로 오는것은 166666번만 연타하면 됨.
 *      즉.. 50만까지 뿐만 아니라, 100만까지의 숫자를 만들어서 비교해봐야됨.
 *      그러면 시간복잡도는 100만*각 자리수를 점검하는것, 대충 계산하면 가장 큰 숫자가 100만(7자리)
 *      니까 700만이 된다. 1억번보다 현저히 작으니까 ㄱㅊ
 *
 * 그렇다면 다음과같이 해결한다.
 * (1) 0 ~ N*2까지 범위 중 이동할 채널 C를 탐색한다.(100만까지도 ㄱㅊ)
 * (2) C번 채널의 자리수중 고장난 버튼이 있는지 검사한다 - String으로 변환해서 한글자씩 확인
 * (3) 고장난 버튼이 없다면 C로 이동하는데 누르는 0~9번 버튼 수와 |N-C|를 통해 +/- 수를 더한다.
 * (4) 구해진 수 중 최솟값을 정답으로 리턴한다.
 */
public class Main {
    static int N, ans=Integer.MAX_VALUE;
    static boolean[] button = new boolean[10];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int cnt = Integer.parseInt(br.readLine());
        if(cnt > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<cnt; i++) button[Integer.parseInt(st.nextToken())] = true;
        }

        if(N == 100) System.out.println(0);
        else {
            for(int i=0; i<=N*2+100; i++) {
                if(!validate(i) && i!=100) continue;
                ans = Math.min(ans, getAns(i));
            }
            System.out.println(ans);
        }
    }

    private static int getAns(int target) {
        //target채널에서 N채널까지 가는 클릭수 계산
        //만약 target이 100이라면 숫자를 누르지 않은것이므로 차이만 더하면됨
        if(target == 100) return Math.abs(target-N);
        else return String.valueOf(target).length()+Math.abs(target-N);
    }

    private static boolean validate(int target) {
        String targetString = String.valueOf(target);
        for(char c : targetString.toCharArray()) {
            if(button[Character.getNumericValue(c)]) return false;
        }
        return true;
    }
}