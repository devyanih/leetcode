import java.util.*;

class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        // Store points as a custom object to keep original coordinates for Manhattan calculation
        Point[] pts = new Point[n];
        for (int i = 0; i < n; i++) {
            pts[i] = new Point(points[i][0], points[i][1], side);
        }
        
        // Sort points based on their position along the perimeter (1D mapping)
        Arrays.sort(pts, (a, b) -> Long.compare(a.linearPos, b.linearPos));
        
        int low = 1, high = 2 * side;
        int ans = 1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canPlace(pts, k, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
    
    private boolean canPlace(Point[] pts, int k, int mid) {
        int n = pts.length;
        // Try starting from different points to handle the circular nature
        // Since we need to find ANY valid set, we check the first few as potential starts
        for (int i = 0; i < n; i++) {
            // Optimization: If the distance between pts[i] and pts[0] is already too large, 
            // we've covered the necessary starting cycles.
            if (i > 0 && pts[i].linearPos - pts[0].linearPos > mid) break;
            
            int count = 1;
            Point lastPoint = pts[i];
            
            for (int j = i + 1; j < n; j++) {
                if (manhattan(lastPoint, pts[j]) >= mid) {
                    count++;
                    lastPoint = pts[j];
                    if (count == k) break;
                }
            }
            
            // Final check: Manhattan distance between the last point and the first point picked
            if (count == k && manhattan(lastPoint, pts[i]) >= mid) {
                return true;
            }
        }
        return false;
    }
    
    private int manhattan(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
    
    static class Point {
        int x, y;
        long linearPos;
        
        Point(int x, int y, int side) {
            this.x = x;
            this.y = y;
            // Map 2D to 1D perimeter position
            if (y == 0) linearPos = x;
            else if (x == side) linearPos = (long)side + y;
            else if (y == side) linearPos = 2L * side + (side - x);
            else linearPos = 3L * side + (side - y);
        }
    }
}