class Solution {
    public int solution(String[] spell, String[] dic) {
        int answer = 2;
        boolean flag = false;
        
        for(String d : dic){
            for(String s : spell){
                if(d.contains(s)) flag = true;
                else flag = false;
                if(flag==false) break;
            }
            if(flag){
                answer = 1;
                break;
            }
        }
        return answer;
    }
}