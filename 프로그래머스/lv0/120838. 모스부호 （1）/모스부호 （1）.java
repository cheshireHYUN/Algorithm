class Solution {    
    public String solution(String letter) {
    	String answer = "";
        String[] morse = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        String[] letterArr = letter.split(" ");
        
        for(String s : letterArr) {
            for(int i = 0; i < morse.length; i++) {
                if(s.equals(morse[i])) {
                    answer += Character.toString(i + 'a');
                }
            }
        }
        return answer;
    }
}