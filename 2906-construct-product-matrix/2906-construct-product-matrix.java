class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int size = n * m;
        int MOD = 12345;
        
        int[] arr = new int[size];
        
        // Step 1: Flatten
        int index = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                arr[index++] = grid[i][j] % MOD;
            }
        }
        
        // Step 2: Prefix product
        int[] prefix = new int[size];
        prefix[0] = 1;
        for(int i = 1; i < size; i++) {
            prefix[i] = (int)((prefix[i-1] * 1L * arr[i-1]) % MOD);
        }
        
        // Step 3: Suffix product
        int[] suffix = new int[size];
        suffix[size-1] = 1;
        for(int i = size - 2; i >= 0; i--) {
            suffix[i] = (int)((suffix[i+1] * 1L * arr[i+1]) % MOD);
        }
        
        // Step 4: Build result
        int[][] result = new int[n][m];
        index = 0;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                result[i][j] = (int)((prefix[index] * 1L * suffix[index]) % MOD);
                index++;
            }
        }
        
        return result;
    }
}