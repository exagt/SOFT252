package classification;

public class StageResults {
    private double totalMarks;
    private int totalCredits;
    private double stage2Average;
    private final int MAXCREDITS = 120;

    /*
     * Constructor. Initialises the instrance variables with zero.
     */
    public StageResults() {
        totalMarks = 0.0;
        totalCredits = 0;
        stage2Average = 0.0;
    }

    /*
     * Returns the stage 2 average.
     */
    public double getStage2Average() {
        return stage2Average;
    }

    /*
     * Returns the total credits added so far.
     */
    public int getTotalCredits() {
        return totalCredits;
    }

    /*
     * Returns the total mark added so far
     */
    public double getTotalMarks() {
        return totalMarks;
    }

    /*
     * takes the stage 2 average as a parameter and places it
     * in the instance variable.
     */
    public void setStage2Average(double stage2Average) {
        this.stage2Average = stage2Average;
    }
    
    /*
     * Returns TRUE of 120 credits have been entered, FALSE otherwise.
     */
    public boolean isComplete() {
        return (totalCredits == MAXCREDITS);
    }
    
    /*
     * Resets the instance variables to zero, destroying any existing
     * values.
     */
    public void resetValues() {
        totalMarks = 0.0;
        totalCredits = 0;
        stage2Average = 0.0;
    }
    
    
}
