class Solution {
    public int[] solution(int[] numbers, String direction) {
         int[] answer = new int[numbers.length];
        if(direction.equals("left")){
            int i=0;
            answer[numbers.length-1] = numbers[0];
            for(int j=1; j<numbers.length; j++) {
                answer[i]=numbers[j];
                i++;
            }
        }else{
            int i=0;
            answer[0] = numbers[numbers.length-1];
            for(int j=1; j<numbers.length; j++) {
                answer[j]=numbers[i];
                i++;
            }
        }
        
       
        return answer;
    }
}