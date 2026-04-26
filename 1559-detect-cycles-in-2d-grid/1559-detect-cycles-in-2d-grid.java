class Solution {
    int m, n;
    boolean[][] visited;
    
    public boolean containsCycle(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    if (dfs(grid, i, j, -1, -1, grid[i][j])) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    private boolean dfs(char[][] grid, int r, int c, int pr, int pc, char ch) {
        visited[r][c] = true;
        
        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};
        
        for (int k = 0; k < 4; k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            
            if (nr < 0 || nc < 0 || nr >= m || nc >= n) continue;
            if (grid[nr][nc] != ch) continue;
            
            // If not visited → explore
            if (!visited[nr][nc]) {
                if (dfs(grid, nr, nc, r, c, ch)) return true;
            } 
            // If visited and NOT parent → cycle
            else if (nr != pr || nc != pc) {
                return true;
            }
        }
        
        return false;
    }
}