import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/** 문자열 폭발
 * 문자열에 폭발문자열이 심어져있고, 폭발시 그 문자는 사라지고 남은 문자열이 합쳐진다.
 * (1) 문자열이 폭발문자열 포함하면 모든 폭발문자열이 사라지고 남은 문자열을 순서대로 이어붙임
 * (2) 새로생긴 문자열에 폭발문자열이 포함되어있을수 있음.
 * (3) 폭발은 폭발문자열이 문자열에 없을때까지 반복된다.
 * 모든 폭발이 끝난후 어떤 문자열이 남는지 구한다. 남아있는 문자가 없다면 "FLURA"를 출력한다.
 *
 * 제한
 * 1<=문자열의 길이<=100만
 * 1<=폭발문자열 길이<=36
 * 두 문자열은 알파벳 소문자, 대문자, 숫자 0~9로 이루어져있음.
 *
 * 풀이
 * Stack에 넣으면 폭발문자열의 몇번째 인덱스까지 순회했는지 currIndex에 저장하고, 다양한 if로 관리해주려 했으나,
 * 폭발 후에 이전 문자열에 대한 currIndex를 구하는데서 오류를 느껴 이 풀이는 실패. (ABC가 폭발인데 ABB가 되면 currIndex구하기 복잡해짐)
 *
 * 따라서 좀 더 쉽게,
 * 문자열의 각 글자가 폭발문자열의 마지막 글자와 동일할경우에만 이전 글자를 탐색해 폭발시키는 방식을 사용한다.
 * (1) Stack에 쌓으면서,
 * (2) 길이가 bomb길이보다 길어지기 시작할때부터 검사를 진행
 * (3) 최근 bomb길이 만큼을 검사하며 폭발문자열이 있다면 pop하고 없다면 지나감
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();
        char[] bomb = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<arr.length; i++) {
            stack.add(arr[i]);
            if(stack.size() < bomb.length) continue;
            //폭발문자열이 없으면 다음으로, 있으면 제거
            boolean isBomb = true;
            for(int j=0; j<bomb.length; j++) {
                if(stack.get(stack.size()-bomb.length+j) != bomb[j]) {
                    isBomb = false;
                    break;
                }
            }
            if(isBomb) {
                for(int k=0; k<bomb.length; k++) stack.pop();
            }
        }
        StringBuilder sb = new StringBuilder();
        if(stack.size()==0) sb.append("FRULA");
        else for(char a : stack) sb.append(a);
        System.out.println(sb);
    }
}