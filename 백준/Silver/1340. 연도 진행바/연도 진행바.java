import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.StringTokenizer;

/** 연도진행바
 * 올해가 몇 %지났는지 출력하라
 * 평년일 때, 각 달은 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31일이 있다. 윤년에는 2월이 29일이다.
 * 윤년은 그 해가 400으로 나누어 떨어지는 해 이거나, 4로 나누어 떨어지면서, 100으로 나누어 떨어지지 않는 해 일때이다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int month = transformMonth(st.nextToken());
        int day = Integer.parseInt(st.nextToken().substring(0,2));
        int year = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(st.nextToken(),":");
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());

        //윤년 변환
        int[] lastDays = new int[]{0,31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if(year%400==0 || (year%4==0 && year%100 !=0)) lastDays[2] = 29;

        //해당 년도를 분으로 변환
        int sumLastDays = 0;
        for(int i : lastDays) sumLastDays+= i;
        int AllTime = sumLastDays*24*60;
        
        //진행된 날짜 구하기
        int nowDays = 0;
        for(int i=1; i<month; i++)  nowDays+=lastDays[i];
        double nowTime = (nowDays+(day-1))*24*60 + 60*hour + minute;

        System.out.println(nowTime*100/AllTime);
    }

    static int transformMonth(String str){
        int result= 0;
        switch (str){
            case("January"): {
                result = 1;
                break;
            }
            case("February"): {
                result = 2;
                break;
            }
            case("March"):{
                result = 3;
                break;
            }
            case("April"):{
                result = 4;
                break;
            }
            case("May"):{
                result = 5;
                break;
            }
            case("June"):{
                result = 6;
                break;
            }
            case("July"):{
                result = 7;
                break;
            }
            case("August"):{
                result = 8;
                break;
            }
            case("September"):{
                result = 9;
                break;
            }
            case("October"):{
                result = 10;
                break;
            }
            case("November"):{
                result = 11;
                break;
            }
            case("December"):{
                result = 12;
                break;
            }
        }
        return result;
    }
}