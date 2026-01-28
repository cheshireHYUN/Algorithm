import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //총 갯수
        int B = Integer.parseInt(st.nextToken()); //검은 조약돌 최대갯수
        int W = Integer.parseInt(st.nextToken()); //하얀 조약돌 최대갯수


        char[] color = new char[N];
        String str = br.readLine();
        for(int i=0; i<N; i++) {
            color[i] = str.charAt(i);
        }

        int start=0, end=0, black=0, white=0;
        int max = Integer.MIN_VALUE;
        while (end<N) {
            if(color[end]=='W') {
                white++;
                end++;
            } else {
                black++;
                while(black>B) {
                    if(color[start] == 'W') white--;
                    else black--;
                    start++;
                }
                end++;
            }
            if(white>=W) max = Math.max(max, end-start);
        }

        if(max == Integer.MIN_VALUE) System.out.println(0);
        else System.out.println(max);
    }
}
