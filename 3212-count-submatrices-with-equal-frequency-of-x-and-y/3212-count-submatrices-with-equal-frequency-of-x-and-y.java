class Solution {
    public int numberOfSubmatrices(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[][] px = new int[m][n];
        int[][] py = new int[m][n];

        int count = 0;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){

                // update counts
                px[i][j] = (grid[i][j] == 'X') ? 1 : 0;
                py[i][j] = (grid[i][j] == 'Y') ? 1 : 0;

                if(i > 0){
                    px[i][j] += px[i-1][j];
                    py[i][j] += py[i-1][j];
                }

                if(j > 0){
                    px[i][j] += px[i][j-1];
                    py[i][j] += py[i][j-1];
                }

                if(i > 0 && j > 0){
                    px[i][j] -= px[i-1][j-1];
                    py[i][j] -= py[i-1][j-1];
                }

                // check condition
                if(px[i][j] == py[i][j] && px[i][j] > 0){
                    count++;
                }
            }
        }

        return count;
    }
}