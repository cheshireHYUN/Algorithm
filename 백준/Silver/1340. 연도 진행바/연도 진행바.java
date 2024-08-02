import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/** 연도진행바
 * 이번해가 몇% 지났는지 계산하라
 * 풀이 : 형변환 주의, 윤년에따라 분모분자 다바뀜주의, 계산결과는 소수점이 필요함에 주의
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int inputMonth = getMonth(st.nextToken());
        String str = st.nextToken();
        int inputDay = Integer.parseInt(str.substring(0, str.length()-1));
        int inputYear = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(st.nextToken(),":");
        int inputHour = Integer.parseInt(st.nextToken());
        int inputMin = Integer.parseInt(st.nextToken());


        int[] monthArr = new int[]{ 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if(inputYear%400==0 || (inputYear%4==0 && inputYear%100!=0)) monthArr[2] = 29;

        //올해의 총 분 구하기
        int allMin=0;
        for(int i : monthArr) allMin+= i;
        allMin = allMin*24*60;

        //지난 시간(분) 구하기
        int nowMin = 0;
        for(int i=1; i<inputMonth; i++) nowMin+= monthArr[i];
        nowMin = (nowMin+inputDay-1)*24*60+inputHour*60+inputMin;

        double result = 100*nowMin/(double)allMin;

        System.out.println(result);

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