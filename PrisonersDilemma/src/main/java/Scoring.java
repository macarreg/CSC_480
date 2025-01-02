public class Scoring {
    public static int[] getScores(boolean[] decisions) {
        int complies = 0;
        int[] scores = {0,0,0};
        for (int i = 0; i < decisions.length; i++) {
            if (decisions[i]) {
                complies++;
            } else {
                scores[i] = 4;
            }
        }

        switch (complies) {
            case 3: return new int[]{3, 3, 3};
            case 2:
                for (int i = 0; i < scores.length; i++){
                    if (scores[i] == 4) {scores[i] = 5;}
                    else { scores[i] = 1;}
                }
            case 1:
                return scores;
            default: return new int[]{1,1,1};
        }
    }
}

