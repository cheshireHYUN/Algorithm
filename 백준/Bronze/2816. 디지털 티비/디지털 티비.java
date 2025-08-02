import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 디지털 티비
 * 1. 화살표를 아래로 내림(채널 i+1로)
 * 2. 화살표를 위로 올림(채널 i-1로)
 * 3. 현재 선택한 채널을 한칸 아래로 옮김(i <-> i+1, 화살표는 아래에 있음)
 * 4. 현재 선택한 채널을 한칸 위로 옮김(i <-> i-1, 화살표는 위에있음)
 * 화살표가 채널 리스트 범위를 넘어가면 명령을 무시한다.
 * 채널 리스트가 주어질때, KBS1을 첫번째로 KBS2를 두번째로 바꾸는 방법을 구하는 프로그램을 작성.
 *
 * 풀이
 * 아래로 내려가다가 KBS1찾아서 올리고 KBS2찾아서 올리고가 구현이 편할듯
 * 따라서 1번이랑 4번만 사용해도 ㄱㅊ
 * 시간복잡도는 채널수가 최대 100개니까... KBS1까지 최대 99번 하강+99번올림, KBS2까지 98번하강, 99번 올림 -> 400이하!
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] arr = new String[N];
        int kbs1Index=0, kbs2Index=0;
        for(int i=0; i<N; i++) {
            String str = br.readLine();
            arr[i] = str;
            if(arr[i].equals("KBS1")) kbs1Index = i;
            else if(arr[i].equals("KBS2")) kbs2Index = i;
        }

        //KBS1 찾아서 올리기
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<kbs1Index; i++) sb.append("1");
        for(int i=0; i<kbs1Index; i++) sb.append("4");

        //KBS2 찾아서 올리기(만약 KBS1이 더 아래에 있었다면 KBS2도 +1증가했음)
        if(kbs1Index > kbs2Index) kbs2Index++;
        if (kbs2Index != 1) {
            for(int i=0; i<kbs2Index; i++) sb.append("1");
            for(int i=0; i<kbs2Index-1; i++) sb.append("4");
        }

        System.out.println(sb);
    }
}
