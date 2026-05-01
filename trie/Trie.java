// Trie implementation in Java
import java.util.*;

public class Trie {

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean endOfWord = false;

        // Optional: store the full word at the terminal node for optimization
        String word = null;
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            int i = ch - 'a';
            if (curr.children[i] == null) {
                curr.children[i] = new TrieNode();
            }
            curr = curr.children[i];
        }
        curr.endOfWord = true;
        curr.word = word;
    }

    public boolean search(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            int i = ch - 'a';
            if (curr.children[i] == null) {
                return false;
            }
            curr = curr.children[i];
        }
        return curr.endOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (char ch : prefix.toCharArray()) {
            int i = ch - 'a';
            if (curr.children[i] == null) {
                return false;
            }
            curr = curr.children[i];
        }
        return true;
    }

    /**
     *  Recursive search for a word in the Trie. This is an alternative to the iterative search method above.
     */
    boolean searchR(String word) {
		return dfs(word, 0, root);
	}

	boolean dfs(String word, int index, TrieNode curr) {
		if(curr == null) {
			return false;
		}
		if(index == word.length()) {
			return curr.endOfWord;
		}
		int i = word.charAt(index) - 'a';
		if(curr.children[i] == null) {
			return false;
		}
		return dfs(word, index+1, curr.children[i]);
	}
}