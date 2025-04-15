import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/** 문자해독
 * W라는 단어를 찾고있다. 단어를 이루는 g개의 그림문자와 마야문자열 S가 주어졌을때
 * 단어 W가 마야문자열 S에 들어있을 수 있는 모든 가짓수를 계산하는 프로그램
 * 즉 문자열 S안에서 W의 순열중 하나가 부분문자열로 들어있는 모든 경우의수
 * 
 * 제한
 * 1<=g<=3000
 * g<=|S|<=300만
 * 
 * 풀이
 * 각 문자열이 S의 부분문자열에 전부 포진해있으면 되는것.
 * 따라서, S를 길이가 g인 부분으로 나눈 뒤(부분문자열:순서보장) W와 같은 문자열들인지 확인하면됨..
 *
 * 모든 부분 문자열을 만들어서 카운트할수도 있지만, 슬라이딩 윈도우로 길이가 g인거만 보는게 더 효율적.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int g = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        char[] gArr = br.readLine().toCharArray();
        char[] sArr = br.readLine().toCharArray();
        Map<Character, Integer> gMap = new HashMap<>();
        Map<Character, Integer> sMap = new HashMap<>();
        for(int i = 0; i < g; i++){
            gMap.put(gArr[i], gMap.getOrDefault(gArr[i], 0)+1);
            sMap.put(sArr[i], sMap.getOrDefault(sArr[i], 0)+1);
        }

        //슬라이딩 윈도우
        int left=0, right=g-1, result=0;
        while(true) {
            boolean isPossible = true;
            //gMap에 해당하는 문자들이 sMap에 전부 있는지 확인
            for(char c : gMap.keySet()) {
                if(!sMap.containsKey(c) || gMap.get(c) != sMap.get(c)) {
                    isPossible = false;
                }
            }
            if(isPossible) result++;
            if(right == s-1) break;
            //슬라이딩 윈도우 - left는 지우고 right++은 채우기
            char c = sArr[left++];
            if(sMap.containsKey(c) && sMap.get(c) == 1) sMap.remove(c);
            else sMap.replace(c, sMap.get(c)-1);
            c = sArr[++right];
            sMap.put(c,sMap.getOrDefault(c,0)+1);
        }

        System.out.println(result);

    }
}