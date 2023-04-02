class Solution {
    public int solution(int M, int N) {
        // MXN크기를 1X1 크기로 자르기위해 필요한 가위질 갯수
        // 일단 가로를 1로 만들고, 세로를 1로만들기 *잘린 가로의 갯수
        int answer = (M-1)+(N-1)*M;
        return answer;
    }
}