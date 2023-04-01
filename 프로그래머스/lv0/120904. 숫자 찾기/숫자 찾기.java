class Solution {
    public int solution(int num, int k) {
        int answer = -1;
        String str = Integer.toString(num);
        String s = Integer.toString(k);
        if(str.contains(s)) answer = str.indexOf(s)+1;
        return answer;
    }
}