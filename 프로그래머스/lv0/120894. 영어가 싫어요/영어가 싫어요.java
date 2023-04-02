class Solution {
    public long solution(String numbers) {
        String[] arr = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        for(int i=0; i<arr.length; i++){
            numbers = numbers.replace(arr[i],Integer.toString(i));
        }
        
        long answer = Long.parseLong(numbers);
        return answer;
    }
}