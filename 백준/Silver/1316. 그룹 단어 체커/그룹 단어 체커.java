import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/** 그룹 단어 체커
 * 그룹단어 : 각 문자가 연속해서 나타나는 경우를 말함
 * cabc의경우 c가 두번 나뉘어 나오므로 그룹단어가 아님
 * 단어 N개를 입력받아 그룹단어의 갯수를 출력하는 프로그램을 작성하라.
 *
 * 풀이 : 해시로 풀면될거같은데! 갯수가 중요한건 아니므로 중복을 허용하지 않는 hashSet을 쓰자.
 * (1) 반복하면서 i가 i-1과 같으면 continue
 * (2) i와 i-1이 다르면 글자가 바뀐것이므로 이전에 나왔던건지 HashSet을 검사한다.
 *      만약 .contains가 true라면 그룹단어가 아닌것으로 종료
 *           .contains가 false라면 계속해서 반복문을 진행한다.
 *
 * 시간복잡도 : 2초 제한인데.. N이 최대 100이니까.. T * O(N)*O(1)인가? 즉 최대 T*100번연산?
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int cnt = 0;
        for(int i=0; i<N; i++){
            HashSet<Character> set = new HashSet<>();
            char[] arr = br.readLine().toCharArray();
            char beforeChar = arr[0];
            set.add(beforeChar);
            boolean flag = true;
            for(int j=1; j<arr.length; j++){
                if(beforeChar == arr[j]) continue;
                else {
                    if(set.contains(arr[j])) {
                        flag = false;
                        break;
                    }
                    else {
                        set.add(arr[j]);
                        beforeChar = arr[j];
                    }
                }
            }
            if (flag) cnt++;
        }
        System.out.println(cnt);
    }
}