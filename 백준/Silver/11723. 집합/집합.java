import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        Set<Integer> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        while(M-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String method = st.nextToken();
            int num;
            switch (method) {
                case "add" :
                    num = Integer.parseInt(st.nextToken());
                    set.add(num);
                    break;
                case "remove" :
                    num = Integer.parseInt(st.nextToken());
                    set.remove(num);
                    break;
                case "check" :
                    num = Integer.parseInt(st.nextToken());
                    sb.append(set.contains(num) ? 1 : 0).append('\n');
                    break;
                case "toggle" :
                    num = Integer.parseInt(st.nextToken());
                    if(set.contains(num)) set.remove(num);
                    else set.add(num);
                    break;
                case "all" :
                    Set<Integer> set2 = new HashSet<>();
                    for(int i=1; i<=20; i++) set2.add(i);
                    set = set2;
                    break;
                case "empty" :
                    set.clear();
                    break;
            }
        }
        System.out.println(sb);
    }
}
