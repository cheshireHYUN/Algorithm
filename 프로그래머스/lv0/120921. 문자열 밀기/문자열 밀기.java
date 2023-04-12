import java.util.*;
class Solution {
    public int solution(String A, String B) {
        int answer = -1, cnt = 0;
        char[] arr = A.toCharArray();
        char[] tmp = new char[arr.length];
        
        if(A.equals(B)) return 0;
        // 한칸씩 뒤로 돌리는 메소드
        for(int k=0; k<arr.length; k++){
            cnt++;
            for(int i=0; i<arr.length; i++){
                if(i+1 == arr.length) tmp[0] = arr[i];
                else { 
                    tmp[i+1] = arr[i];
                }
            }
            if(String.valueOf(tmp).equals(B)){
                answer = cnt;
                break;
            }
            //arr = tmp; 배열 같다고 하면 안됨 - 얕은복사되므로 반복문에서 값 이상해진다
            arr = tmp.clone();
        }
        
        return answer;
    }
}