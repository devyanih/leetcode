class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        if (rows == 1) return encodedText;
        
        int n = encodedText.length();
        int cols = n / rows;
        
        char[][] mat = new char[rows][cols];
        
        // fill matrix row-wise
        int idx = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mat[i][j] = encodedText.charAt(idx++);
            }
        }
        
        StringBuilder result = new StringBuilder();
        
        // traverse diagonals
        for (int startCol = 0; startCol < cols; startCol++) {
            int i = 0, j = startCol;
            
            while (i < rows && j < cols) {
                result.append(mat[i][j]);
                i++;
                j++;
            }
        }
        
        // remove trailing spaces
        int end = result.length() - 1;
        while (end >= 0 && result.charAt(end) == ' ') {
            end--;
        }
        
        return result.substring(0, end + 1);
    }
}