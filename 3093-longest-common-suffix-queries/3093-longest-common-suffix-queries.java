class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];

        // store best index for this suffix
        int index = -1;
        int len = Integer.MAX_VALUE;
    }

    private void update(TrieNode node, int idx, int length) {
        // choose smaller length
        // if same length choose earlier index
        if (length < node.len || (length == node.len && idx < node.index)) {
            node.len = length;
            node.index = idx;
        }
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {

        TrieNode root = new TrieNode();

        // Build Trie using reversed words
        for (int i = 0; i < wordsContainer.length; i++) {

            String word = wordsContainer[i];
            int n = word.length();

            TrieNode curr = root;

            // update root for empty suffix case
            update(curr, i, n);

            // insert reversed word
            for (int j = n - 1; j >= 0; j--) {

                int c = word.charAt(j) - 'a';

                if (curr.children[c] == null) {
                    curr.children[c] = new TrieNode();
                }

                curr = curr.children[c];

                update(curr, i, n);
            }
        }

        int[] ans = new int[wordsQuery.length];

        // Process queries
        for (int i = 0; i < wordsQuery.length; i++) {

            String q = wordsQuery[i];
            TrieNode curr = root;

            // traverse reversed query
            for (int j = q.length() - 1; j >= 0; j--) {

                int c = q.charAt(j) - 'a';

                if (curr.children[c] == null) {
                    break;
                }

                curr = curr.children[c];
            }

            ans[i] = curr.index;
        }

        return ans;
    }
}