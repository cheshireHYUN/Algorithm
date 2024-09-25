import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 직각삼각형
 * 이집트인들은 가 ㄱ변의 길이가 3,4,5인 삼각형이 직각 삼각형인것을 알아냈다.
 * 주어진 세 변의 길이로 삼각형이 직각인지 아닌지 구분하세요.
 * 입력 : 여러개의 테케가 주어지며 막줄은 0 0 0, 각 테케는 3만보다 작은 양의 정수
 * 풀이 : 세변길이 공식 사용해서 맞으면 right 틀리면 wrong출력
 * 세변을 담은 배열을 정렬해서 arr[2]^2 = arr[0]^2+arr[1]^2
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while(true){
            st = new StringTokenizer(br.readLine());
            int[] numbers = new int[3];
            for(int i=0; i<3; i++) numbers[i] = Integer.parseInt(st.nextToken());
            if(numbers[0] == 0 && numbers[1] == 0 && numbers[2] == 0 ) break;
            Arrays.sort(numbers);
            boolean isTriangle = (Math.pow(numbers[2],2) == Math.pow(numbers[0],2)+Math.pow(numbers[1],2));
            if(isTriangle) sb.append("right").append('\n');
            else sb.append("wrong").append('\n');
        }
        System.out.println(sb);
    }
}