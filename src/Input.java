import java.util.Scanner;

public class Input {
    private int n, m, s, f;
    Scanner scanner;
    int [][] transitions;
    int [] sourceStates;
    int [] finalStates;

    public Input (Scanner scanner) {
        this.scanner = scanner;

        n = scanner.nextInt();
        m = scanner.nextInt();
        s = scanner.nextInt();
        f = scanner.nextInt();

        transitions = new int[n][m];
        sourceStates = new int[s];
        finalStates = new int[f];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                transitions[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i < s; i++) {
            sourceStates[i] = scanner.nextInt();
        }

        for (int i = 0; i < f; i++) {
            finalStates[i] = scanner.nextInt();
        }
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public int getS() {
        return s;
    }

    public int getF() {
        return f;
    }

    public int[][] getTransitions() {
        return transitions;
    }

    public int[] getSourceStates() {
        return sourceStates;
    }

    public int[] getFinalStates() {
        return finalStates;
    }
}
