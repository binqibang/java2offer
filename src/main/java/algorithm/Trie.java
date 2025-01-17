package algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode208 前缀树
 */
public class Trie {
    private Map<Character, Trie> children;
    private boolean isEnd;

    public Trie() {
        children = new HashMap<>();
        isEnd = false;
    }

    public void insert(String word) {
        Trie root = this;
        for (char ch : word.toCharArray()) {
            root.children.computeIfAbsent(ch, k -> new Trie());
            root = root.children.get(ch);
        }
        root.isEnd = true;
    }

    public boolean search(String word) {
        Trie endNode = searchPrefix(word);
        return endNode != null && endNode.isEnd;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private Trie searchPrefix(String prefix) {
        Trie root = this;
        for (char ch : prefix.toCharArray()) {
            if (root.children.get(ch) == null) {
                return null;
            }
            root = root.children.get(ch);
        }
        return root;
    }
}
