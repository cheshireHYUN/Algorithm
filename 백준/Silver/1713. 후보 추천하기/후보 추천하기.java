import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Student implements Comparable<Student> {
        int num;
        int cnt;
        int timeStamp;

        public Student(int num, int cnt, int timestamp) {
            this.num = num;
            this.cnt = cnt;
            this.timeStamp = timestamp;
        }

        @Override
        public int compareTo(Student student) {
            int result = student.cnt - this.cnt; //추천횟수 내림차순
            if(result == 0) return student.timeStamp - this.timeStamp; //시간 내림차순
            return result;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<Student> frames = new ArrayList<>();
        Student[] students = new Student[101];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            int num = Integer.parseInt(st.nextToken());

            if(students[num] != null) students[num].cnt++;
            else {
                Collections.sort(frames);
                if(frames.size()==N) {
                    Student s = frames.remove(N-1);
                    students[s.num] = null;
                }
                students[num] = new Student(num,1,i);
                frames.add(students[num]);
            }
        }

        Collections.sort(frames, (o1,o2) -> {
            return o1.num - o2.num;
        });
        StringBuilder sb = new StringBuilder();
        for(Student s : frames) sb.append(s.num).append(' ');
        System.out.println(sb);
    }
}
