class Solution {
    public int solution(int[] sides) {
        int smin = Math.min(sides[0],sides[1]);
        int smax = Math.max(sides[0],sides[1]);
        // 가장긴변 < 나머지두변의 합
        int cnt = 0;
        // smax가 가장 긴 변일경우
        for(int i=smax-smin+1; i<=smax; i++) cnt++;
        // 나머지한변이 가장 긴 변일경우 
        for(int i=smax+1; i<smax+smin; i++) cnt++;
        return cnt;
    }
}