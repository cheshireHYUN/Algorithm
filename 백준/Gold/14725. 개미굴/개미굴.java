import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 개미굴
 로봇개미는 더이상 내려갈 수 없으면 그자리에서 움직이지 않고 신호를 보낸다,
 신호를 바탕으로 개미굴을 그려보자
 - 먹이정보는 알파벳 대문자로만 이뤄져있다
 - 각 층을 "--"로 구분후며 같은층에 여러방이 있을땐 사전순서가 앞서는 먹이정보가 먼저다.

 풀이
 트라이 자료구조를 활용해 입력해주고 구조를 출력한다.
 - 한 노드에 한 문자가 아닌 문자열을 넣는다
 - terminal노드의 여부를 판단해줄 필요가 없다
 - 메소드는 트라이의 데이터를 넣어주는 insert()와 구조를 출력하는 print()가 필요하다.

 */
public class Main {
    static StringBuilder sb = new StringBuilder();
    static class TrieNode {
        Map<String, TrieNode> childNode = new HashMap<>();

        public void insert(String strs) {
            TrieNode trieNode = this;
            String[] str = strs.split(",");
            for(String s : str) {
                trieNode.childNode.putIfAbsent(s, new TrieNode());
                trieNode = trieNode.childNode.get(s);
            }
        }

        public void print(TrieNode curr, int depth) {
            TrieNode trieNode = curr;
            if(trieNode.childNode!=null) {
                List<String> list = new ArrayList<>(trieNode.childNode.keySet());
                Collections.sort(list);
                for(String str : list) {
                    for(int i=0; i<depth; i++) sb.append("--");
                    sb.append(str).append('\n');
                    print(trieNode.childNode.get(str), depth+1);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        TrieNode trie = new TrieNode();
        for(int i=0; i<n; i++) {
            String[] input = br.readLine().split(" ");
            int k = Integer.parseInt(input[0]);

            StringBuilder sb = new StringBuilder();
            for(int j=1; j<k+1; j++) {
                sb.append(input[j]+",");
            }

            trie.insert(sb.toString());
        }

        trie.print(trie,0);
        System.out.println(sb);

    }
}
