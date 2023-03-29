import java.util.*;
class Solution {
    public int[] solution(int n) {
        int[] arr = new int[n+1];
        List<Integer> list = new ArrayList<>();
        //에라토스테네스의 체 - 소수찾기
        for(int i=2; i<=n; i++){
            if(arr[i]==0){
                list.add(i);
                for(int j=i; j<=n; j+=i){
                    arr[j]=1;
                }
            }
        }
        //찾은 소수list중에서 약수찾아서 리턴하기
        List<Integer> list2 = new ArrayList<>();
        for(int i : list) {
            if(n%i==0) list2.add(i);
        }
        int[] answer = list2.stream().mapToInt(Integer::intValue).toArray();
        
        return answer;
    }
}