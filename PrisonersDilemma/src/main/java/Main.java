import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // List of strategies
        List<Strategy> strategies = Arrays.asList(
                new AdaptiveOpportunist("AdaptiveOpportunist"),
                new CalculatedContender("CalculatedContender"),
                new AdaptiveAltruist("Adaptive Altruist"),
                new AlwaysComply("AlwaysComply"),
                new AlwaysDefect("AlwaysDefect"),
                new TitForTat("TitForTat"),
                new NiceTitForTat("NiceTat"),
                new StingyTitForTat("NastyTat"),
                new TwoTats("TwoTats"),
                new TwoTits("TwoTits"),
                new TitTestTat("Tester"),
                new AndFirst("AndFirst"),
                new OrFirst("OrFirst"),
                new GrimTrigger("GrimTrigger"),
                new RandomChoice("Random")
        );

        // Generate unique pairs
        List<int[]> pairs = new ArrayList<>();
        for (int i = 0; i < strategies.size(); i++) {
            for (int j = i; j < strategies.size(); j++) {
                pairs.add(new int[]{i, j});
            }
        }

        // Run tournament
        int[][] results = new int[strategies.size()][pairs.size()];
        StrategyRunner.runStrategies(strategies, results, "3PlayerDilemma");

        // Assemble and print the table
        TableBuilder.assembleAndPrintTable(strategies, results, pairs);
    }
}
