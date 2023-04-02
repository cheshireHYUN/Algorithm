class Solution {
    public int solution(int chicken) {
        // 치킨수만큼 쿠폰 받음
        int cpn = chicken;
        // 10장 이상이면 치킨 서비스받음 + 서비스 치킨에도 쿠폰발급
        int service = 0;
        while(cpn>=10){
            service += cpn/10; // 서비스치킨(누적) : 쿠폰/10
            cpn = cpn%10+cpn/10; // 현재쿠폰 : 남은쿠폰 + 방금시킨 서비스치킨갯수
        }
        
        return service;
    }
}