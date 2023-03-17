import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        // map으로 선언해야될것같은데 키:값이 A:6 이렇게
        HashMap<String,Integer> tMap = new HashMap<String,Integer>();
        for(String t : terms){
            String[] tArr = t.split(" "); //tArr[0]=약관유형, tArr[1]=보관기간
            tMap.put( tArr[0],Integer.parseInt(tArr[1]) );
        }
        
        List<Integer> ansList = new ArrayList<>();
        int order = 0;
        // 모든달은 28일!!
        for(String p : privacies){
            order++;
            String[] pArr = p.split(" "); //pArr[0]=날짜, pArr[1]=약관유형
            String[] pDate = pArr[0].split("\\."); //pDate[i] = 년,월,일
            //약관유형이 같은경우, 날짜+보관기간이 today보다 전인지 확인한다.
            
            int tmp = Integer.parseInt(pDate[1]) + tMap.get(pArr[1]);
            int cnt = 0;
            //다음년도로 넘어가는 경우 처리
            if(tmp > 12){
                // 월이 12를 넘어가면 햇수가 넘어가야함
                // 예를들어 5월+23개월 = 28월, 24를 빼서 4월로 처리
                while(tmp > 12){
                    tmp -= 12;
                    cnt++;
                }
            }
            // 개월 처리
            int newY = Integer.parseInt(pDate[0])+cnt;
            int newM = tmp;
            int newD = Integer.parseInt(pDate[2]);
            // 일자처리 : 전달로 바뀔경우
            if(newD-1 == 0){
                // 월 -1해주고, 일 -1해주고
                newD = 28;
                if(newM-1 == 0){
                    //월이 0이니까 전년도 12월로
                    newY -= 1;
                    newM = 12;
                }else{
                    newM -= 1;
                }
               
            } else{
                newD -= 1;
            }
                               
            
            System.out.println(""+newY+"년"+newM+"월"+newD+"일");
            // pDate의 값보다 큰 날짜면 true
            String[] todayArr = today.split("\\.");
            
            if(Integer.parseInt(todayArr[0]) > newY){
                ansList.add(order);
            } else if(Integer.parseInt(todayArr[0]) == newY 
                      && Integer.parseInt(todayArr[1]) > newM) {
                 ansList.add(order);
            } else if(Integer.parseInt(todayArr[0]) == newY
                      && Integer.parseInt(todayArr[1]) == newM
                     && Integer.parseInt(todayArr[2]) > newD) {
                 ansList.add(order);
            }
            
        }
        
        int[] answer = new int[ansList.size()];
        int k=0;
        for(int a : ansList){
            answer[k] = a;
            k++;
        }
        return answer;
    }
}

