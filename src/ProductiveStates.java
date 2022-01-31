import java.util.*;

public class ProductiveStates {
    int[][] transitions;
    int[] finalStates;
    Set<Integer> productiveStates;

    public ProductiveStates(int[][] transitions, int[] finalStates) {
        this.transitions = transitions;
        this.finalStates = finalStates;

        productiveStates = new HashSet<>();
        boolean [] visited = new boolean[transitions.length];

        for (Integer state : finalStates) {
            productiveStates.add(state);
            visited[state] = true;
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < transitions.length; i++) {
            if (!visited[i]) {
                stack.add(i);
                visited[i] = true;
                while (!stack.isEmpty()) {
                    int curr = stack.peek();

                    boolean discard = true;
                    for (int j  = 0; j < transitions[curr].length; j++) {
                        if (productiveStates.contains(transitions[curr][j])) {
                            productiveStates.addAll(stack);
                            break;
                        } else if (!visited[transitions[curr][j]]) {
                            stack.add(transitions[curr][j]);
                            visited[transitions[curr][j]] =  true;
                            discard = false;
                            break;
                        }
                    }

                    if (discard) {
                        stack.pop();
                    }
                }
            }
        }

    }

    public Set<Integer> getProductiveStates() {
        return productiveStates;
    }
}