import java.util.LinkedList;

public class ParallelDfs {
    int[][] transitions;
    boolean [] visited;

    public ParallelDfs(int[][] transitions) {
        this.transitions = transitions;
        this.visited = new boolean[transitions.length];
    }

    public LinkedList<Integer> getSequence(int s1, int s2, LinkedList<Integer> sequence) {
        visited[s1] = true;
        visited[s2] = true;
        int newS1, newS2;

        LinkedList<Integer> oldSequence = new LinkedList<>(sequence);

        for (int i = 0; i < transitions[s1].length; i++) {
            newS1 = transitions[s1][i];
            newS2 = transitions[s2][i];

            if (newS1 == newS2) {
                sequence.add(i);
                return sequence;
            }

            if (visited[newS1] || visited[newS2]) {
                continue;
            }

            sequence.add(i);
            LinkedList<Integer> res = getSequence(newS1, newS2, sequence);

            if (res != null) {
                return res;
            } else {
                sequence.removeLast();
                visited[newS1] = false;
                visited[newS2] = false;
            }
        }

        sequence.clear();
        sequence.addAll(oldSequence);

        return null;
    }

    public LinkedList<Integer> getSequenceSpecial(int s1, int s2, LinkedList<Integer> sequence, LinkedList<Integer> finalStates) {
        visited[s1] = true;
        visited[s2] = true;
        int newS1, newS2;

        LinkedList<Integer> oldSequence = new LinkedList<>(sequence);

        for (int i = 0; i < transitions[s1].length; i++) {
            newS1 = transitions[s1][i];
            newS2 = transitions[s2][i];

            if (newS1 == newS2 && finalStates.contains(newS1)) {
                sequence.add(i);
                return sequence;
            }

            if (visited[newS1] || visited[newS2]) {
                continue;
            }

            sequence.add(i);
            LinkedList<Integer> res = getSequence(newS1, newS2, sequence);

            if (res != null) {
                return res;
            } else {
                sequence.removeLast();
                visited[newS1] = false;
                visited[newS2] = false;
            }
        }

        sequence.clear();
        sequence.addAll(oldSequence);

        return null;
    }
}
