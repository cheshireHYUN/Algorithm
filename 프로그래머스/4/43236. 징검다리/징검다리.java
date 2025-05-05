/**
50억에 대한 조합은 너무 크므로 완탐 불가 -> 이분탐색
각 지점 사이 거리의 최솟값을 mid로 두고, 역으로 mid보다 작은 값을 가진 바위들을 부숨으로써 n개 부수는 경우 찾기
이때 mid중 가장 큰 값을 리턴해야 하므로 upperbound-1?
**/
import java.util.*;
class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int left=0, right=distance+1;
        while(left<right) {
            int mid = (right+left)/2;
            if(n < getBombRock(mid,rocks,distance)) right = mid;
            else left = mid+1;
        }
        
        return left-1;
    }
        
    public int getBombRock(int min, int[] rocks, int distance) {
        int cnt = 0, pre = 0;
        for(int i=0; i<rocks.length+1; i++) {
            if(i==rocks.length) {
                if(distance-pre < min) cnt++;
                break;
            }
            if(rocks[i]-pre < min) cnt++;
            else pre=rocks[i];
        }
        return cnt;
    }
}