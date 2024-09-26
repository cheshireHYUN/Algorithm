import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 나이순 정렬
 * 나이와 이름이 가입순으로 주어질때
 * 회원의 나이가 증가하는 순, 같으면 먼저 가입한 사람이 앞에 오는 순서로 정렬하라.
 */
public class Main {
    private static class User implements Comparable<User>{
        String name;
        int age;
        int order;
        public User(int a, String n, int o){
            name = n;
            age = a;
            order = o;
        }
        @Override
        public int compareTo(User o) {
            if(o.age == this.age) return this.order-o.order;
            return this.age-o.age;
        }
        @Override
        public String toString() {
            return age+" "+name;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        User[] arr = new User[N];
        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = new User(Integer.parseInt(st.nextToken()), st.nextToken(), i);
        }
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for(User user : arr) sb.append(user).append('\n');
        System.out.println(sb);
    }
}