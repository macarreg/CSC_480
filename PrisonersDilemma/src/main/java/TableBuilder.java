import java.util.List;

public class TableBuilder {
    public static void assembleAndPrintTable(List<Strategy> strategies, int[][] results, List<int[]> pairs) {
        StringBuilder table = new StringBuilder();

        // Generate the header row
        table.append("Pair\t"); // Header for pair column
        for (Strategy strategy : strategies) {
            table.append(strategy.getName() + "\t");
        }
        table.append("\n");

        // Populate the table rows
        for (int pairIndex = 0; pairIndex < pairs.size(); pairIndex++) {
            int[] pair = pairs.get(pairIndex);
            String pairName = "(" + strategies.get(pair[0]).getName() + ", " + strategies.get(pair[1]).getName() + ")\t";
            table.append(pairName); // Add pair name

            for (int strategyIndex = 0; strategyIndex < strategies.size(); strategyIndex++) {
                table.append(results[strategyIndex][pairIndex] + "\t"); // Add scores
            }
            table.append("\n");
        }

        // Print the table
        System.out.println(table.toString());
    }
}
