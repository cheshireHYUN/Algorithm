class Solution {
    public int solution(String my_string) {
        String[] arr = my_string.split(" ");
        int i=1, answer = Integer.parseInt(arr[0]);
        while(i<arr.length){
            if(arr[i].equals("+")){
                answer += Integer.parseInt(arr[i+1]);
            } else if(arr[i].equals("-")){
                answer -= Integer.parseInt(arr[i+1]);
            }
            i += 2;
        }
        return answer;
    }
}