import java.util.Scanner;

public class Main {
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        Input input = new Input(scanner);
	
	// solve just the wanted problem
        switch (args[0]) {
            case "accessible": {
                AccessibleStates accessibleStates = new AccessibleStates(input.getTransitions(), input.getSourceStates());
                for (Integer state : accessibleStates.getAccessibleStates()) {
                    System.out.println(state);
                }
                break;
            }
            case "productive": {
                ProductiveStates productiveStates = new ProductiveStates(input.getTransitions(), input.getFinalStates());
                for (Integer state : productiveStates.getProductiveStates()) {
                    System.out.println(state);
                }
                break;
            }
            case "useful": {
                AccessibleStates accessibleStates = new AccessibleStates(input.getTransitions(), input.getSourceStates());
                ProductiveStates productiveStates = new ProductiveStates(input.getTransitions(), input.getFinalStates());

                for (Integer state : accessibleStates.getAccessibleStates()) {
                    if (productiveStates.getProductiveStates().contains(state)) {
                        System.out.println(state);
                    }
                }
                break;
            }
            case "synchronize": {
                Synchronizer sync = new Synchronizer(input.getTransitions(),
                        input.getFinalStates(),
                        input.getSourceStates());
                String sequence = sync.getSequence();
                System.out.println(sequence);
                break;
            }
        }
    }
}