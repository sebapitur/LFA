import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class AccessibleStates {
    int [][] transitions;
    int [] sourceStates;
    Set<Integer> accessibleStates;

    public AccessibleStates(int [][] transitions, int [] sourceStates) {
        this.sourceStates = sourceStates;
        this.transitions = transitions;

        accessibleStates = new HashSet<>();
        Queue<Integer> reachableStates = new LinkedList<>();
        boolean [] visited = new boolean[transitions.length];

        for (Integer source : sourceStates) {
            visited[source] = true;
            reachableStates.add(source);
        }

        while (!reachableStates.isEmpty()) {
            Integer curr = reachableStates.element();
            accessibleStates.add(curr);
            reachableStates.remove();

            for (int i = 0; i < transitions[curr].length; i++) {
                if (!visited[transitions[curr][i]]) {
                    reachableStates.add(transitions[curr][i]);
                    visited[transitions[curr][i]] = true;
                }
            }
        }
    }

    public Set<Integer> getAccessibleStates() {
        return accessibleStates;
    }
}
