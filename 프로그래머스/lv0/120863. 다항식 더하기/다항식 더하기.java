import java.util.*;
class Solution {
    public String solution(String polynomial) {
        String[] strArray = polynomial.split("\\+");
        int tmpX=0, tmp=0;
        for(String str : strArray){
            str = str.replace(" ","");
            
            if(str.contains("x")) {
                if(str.equals("x")) str="1x";
                tmpX += Integer.parseInt(str.replace("x",""));
            } else {
                tmp += Integer.parseInt(str);
            }
        }
        
        //동류항끼리 계산 끝났으므로 answer 형태로 조립
        String answer ="";
        if(tmpX>1) {
            answer = tmpX+"x";
            if(tmp>0) answer += " + "+tmp;
        }
        else if(tmpX==1) {
            answer = "x";
            if(tmp>0) answer += " + "+tmp;
        }else if(tmpX==0){
            if(tmp>0) answer += tmp;
        }
        
        
        
        return answer;
    }
}