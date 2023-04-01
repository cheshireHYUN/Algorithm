import java.util.*;
class Solution {
    public int[] solution(int[][] score) {
        //내림차순 배열 만들고 .indexOf로 등수 기져오기
        Double[] avg = new Double[score.length];
        for(int i=0; i<score.length; i++){
            avg[i] = ((double)score[i][0]+(double)score[i][1])/2;
        }
        
        Double[] avgSort = avg.clone();
        Arrays.sort(avgSort,Collections.reverseOrder());
        List<Double> list = Arrays.asList(avgSort);
        
        int[] answer = new int[score.length];
        for(int i=0; i<score.length; i++) {
        	answer[i] = list.indexOf(avg[i])+1;
        }
        return answer;
    }
}