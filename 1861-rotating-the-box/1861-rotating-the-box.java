class Solution {
    public char[][] rotateTheBox(char[][] boxGrid) {

        int m = boxGrid.length;
        int n = boxGrid[0].length;

        // Step 1: Apply gravity
        for (int i = 0; i < m; i++) {

            int empty = n - 1;

            for (int j = n - 1; j >= 0; j--) {

                // Obstacle found
                if (boxGrid[i][j] == '*') {
                    empty = j - 1;
                }

                // Stone found
                else if (boxGrid[i][j] == '#') {

                    // Move stone to empty position
                    boxGrid[i][j] = '.';
                    boxGrid[i][empty] = '#';

                    empty--;
                }
            }
        }

        // Step 2: Rotate 90 degree clockwise
        char[][] result = new char[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                result[j][m - 1 - i] = boxGrid[i][j];
            }
        }

        return result;
    }
}