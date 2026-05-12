class Solution {

    public int minimumEffort(int[][] tasks) {

        // Sort by (minimum - actual) descending
        Arrays.sort(tasks, (a, b) ->
                (b[1] - b[0]) - (a[1] - a[0]));

        int energy = 0;
        int answer = 0;

        for (int[] task : tasks) {

            int actual = task[0];
            int minimum = task[1];

            // Need extra energy
            if (energy < minimum) {

                answer += (minimum - energy);
                energy = minimum;
            }

            // Finish task
            energy -= actual;
        }

        return answer;
    }
}