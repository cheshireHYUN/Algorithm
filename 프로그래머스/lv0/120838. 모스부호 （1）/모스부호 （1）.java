class Solution {    
    public String solution(String letter) {
        String[] morse = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        String answer = "";
        String[] letterArray = letter.split(" ");
        for(String s : letterArray){
            for(int i=0; i<morse.length; i++){
                if(s.equals(morse[i])){
                    answer += Character.toString(i+'a');
                    break;
                }
            }
        }

        return answer;
    }
}