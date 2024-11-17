package use_case.OptimusPrime;

/**
 * The Input Data for the Optimus Prime chat Use Case.
 */
public class OptimusPrimeInputData {
    private final String username;

    public OptimusPrimeInputData(String username) {
        this.username = username;
    }

    String getUsername() {
        return username;
    }
}
