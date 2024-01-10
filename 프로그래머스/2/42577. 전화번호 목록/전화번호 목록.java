import java.util.*;
/*
    한번호가 다른번호의 접두어인 경우가 있는지 확인하자.
    phone_book의 전화번호중 위 경우가 있으면 false를 아니면 true를 리턴하자.
    startsWith() 메소드를 사용하여 체크하면 될듯
    
    123과 1234가 있을때, 1234탐색시 hashmap.get(123)을 통해서 123이 있는지 확인해야할것.
    전화번호 길이가 20까지이니까 최대 20번 get을 하겠네 괜찬ㄹ은데??
*/
class Solution {
    public boolean solution(String[] phone_book) {
        Map<String, Integer> map = new HashMap<>();
        for(String phone : phone_book) map.put(phone, 1);
        for(String phone : phone_book){
            //각 폰번호의 모든 접두어 경우의수들에 대해 map에서 get해보기 (1개 이상이면 false 리턴하기)
            for(int i=1; i<phone.length(); i++){
                if(null!=map.get(phone.substring(0,i))) return false;
            }
        }
        return true;
    }
}