import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 연도 진행바
 * 이번해가 얼마나 지났는지 출력하는 프로그램 작성
 * 각 달은 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
 * 윤년엔  31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
 * 윤년은 400으로 나누어 떨어지거나 4로나누어떨어지면서 100으로 나누어 떨어지지 않는 해임
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nowMonth = getMonth(st.nextToken());
        String d = st.nextToken();
        int nowDay = Integer.parseInt(d.substring(0,d.length()-1));
        int nowYear = Integer.parseInt(st.nextToken());
        String[] h = st.nextToken().split(":");
        int nowHour = Integer.parseInt(h[0]);
        int nowMin = Integer.parseInt(h[1]);
        
        //윤년인지 판단하여 분모, 분자를 구함
        int[] allMonth = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if(isYear(nowYear)) allMonth[2] = 29;

        int fullMin = getFullMin(allMonth);

        int nowDays = 0;
        for(int i=1; i<nowMonth; i++) nowDays+= allMonth[i];

        int now = (nowDays + nowDay-1)*24*60 + nowHour*60 + nowMin;

        double result = ((double) now) / fullMin * 100;

        System.out.println(result);
    }

    private static int getFullMin(int[] allMonth){
        int sumDays = 0;
        for(int m : allMonth) sumDays+=m;
        //전체일수 * 24시간 * 60분
        return sumDays*24*60;
    }

    private static boolean isYear(int nowYear){
        if(nowYear%400==0 || (nowYear%4==0 && nowYear%100!=0)) return true;
        return false;
    }

    private static int getMonth(String str){
        switch (str){
            case "January" : {
                return 1;
            }
            case "February" : {
                return 2;
            }
            case "March" : {
                return 3;
            }
            case "April" : {
                return 4;
            }
            case "May" : {
                return 5;
            }
            case "June" : {
                return 6;
            }
            case "July" : {
                return 7;
            }
            case "August" : {
                return 8;
            }
            case "September" : {
                return 9;
            }
            case "October" : {
                return 10;
            }
            case "November" : {
                return 11;
            }
            case "December" : {
                return 12;
            }
        }
        return 0;
    }
}