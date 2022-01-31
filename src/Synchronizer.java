import java.util.*;

public class Synchronizer {
    int[][] transitions;
    int[] finalStates;
    int [] sourceStates;
    LinkedList<Integer> sequence;

    public Synchronizer(int[][] transitions, int[] finalStates, int[] sourceStates) {
        this.transitions = transitions;
        this.finalStates = finalStates;
        this.sourceStates = sourceStates;

        sequence = new LinkedList<>();

        boolean found = false;
        List<Integer> states = new ArrayList<>();
        LinkedList<Integer> tempSeq = null;

        if (sourceStates.length == 0) {
            for (int i = 0; i < transitions.length; i++) {
                states.add(i);
            }
        } else {
            for (int sourceState : sourceStates) {
                states.add(sourceState);
            }
        }

        LinkedList<Integer> finalStatesList = null;

        if (finalStates.length != 0) {
            finalStatesList = new LinkedList<>();

            for (int state : finalStates) {
                finalStatesList.add(state);
            }
        }

        if (finalStates.length == 0) {
            while (states.size() > 1) {
                int s1, s2;
                s1 = states.get(0);
                s2 = states.get(1);

                tempSeq = findSequence(s1, s2);

                if (tempSeq != null) {
                    sequence.addAll(tempSeq);
                    states.remove(0);

                    for (int i = 0; i < states.size(); i++) {
                        states.set(i, changeForInput(states.get(i), tempSeq));
                    }

                    Set<Integer> uniqueStates = new HashSet<>(states);
                    states = new LinkedList<>(uniqueStates);
                } else {
                    break;
                }
            }

            if (tempSeq == null) {
                sequence = null;
            }
        } else {
            while (states.size() > 1) {
                int s1, s2;
                s1 = states.get(0);
                s2 = states.get(1);

                tempSeq = findSequenceSpecial(s1, s2, finalStatesList);

                if (tempSeq != null) {
                    sequence.addAll(tempSeq);
                    states.remove(0);

                    for (int i = 0; i < states.size(); i++) {
                        states.set(i, changeForInput(states.get(i), tempSeq));
                    }

                    Set<Integer> uniqueStates = new HashSet<>(states);
                    states = new LinkedList<>(uniqueStates);
                } else {
                    break;
                }
            }

            if (tempSeq == null) {
                sequence = null;
            }
        }
    }

    private Integer changeForInput(Integer state, LinkedList<Integer> tempSeq) {
        for (int i : tempSeq) {
            state = transitions[state][i];
        }

        return state;
    }

    private LinkedList<Integer> findSequence(int s1, int s2) {
        ParallelDfs dfs = new ParallelDfs(transitions);
        return dfs.getSequence(s1, s2, new LinkedList<>());
    }

    private LinkedList<Integer> findSequenceSpecial(int s1, int s2, LinkedList<Integer> finalStates) {
        ParallelDfs dfs = new ParallelDfs(transitions);
        return dfs.getSequenceSpecial(s1, s2, new LinkedList<>(), finalStates);
    }

    public String getSequence() {
        if (sequence != null && sequence.size() > 0) {
            StringBuilder ret = new StringBuilder();
            ret.append(sequence.get(0));

            for (int i = 1; i < sequence.size(); i++) {
                ret.append(" ").append(sequence.get(i));
            }

            return ret.toString();
        } else {
            return null;
        }
    }
}
