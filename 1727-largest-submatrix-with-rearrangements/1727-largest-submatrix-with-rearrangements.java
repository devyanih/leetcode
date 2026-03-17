import java.util.*;

class Solution {
    public int largestSubmatrix(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        int ans = 0;

        for(int r = 1; r < m; r++){
            for(int c = 0; c < n; c++){
                if(matrix[r][c] == 1){
                    matrix[r][c] += matrix[r-1][c];
                }
            }
        }

     
        for(int r = 0; r < m; r++){

            int[] row = matrix[r].clone();
            Arrays.sort(row);

            for(int c = 0; c < n; c++){
                int height = row[c];
                int width = n - c;
                ans = Math.max(ans, height * width);
            }
        }

        return ans;
    }
}