package classification;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StageResultsTest {
    private StageResults empty;     // will have no credits and no marks
    private StageResults full;      // will have 120 credits and marks
    private StageResults halfFull;  // will have 60 credits and some marks
    
    public StageResultsTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        // empty - object that starts with default values
        empty = new StageResults();

        // full - object with 120 credits-worth of marks but no
        // initial stage2Average
        full = new StageResults();
        full.addModuleMark(120, 50.0);

        // halfFull - object with 60 credits worth of marks and
        // no initial stage2Average
        halfFull = new StageResults();
        halfFull.addModuleMark(60, 50.0);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetStage2Average() {
    }

    @Test
    public void testGetTotalCredits() {
    }

    @Test
    public void testGetTotalMarks() {
    }

    @Test
    public void testSetStage2Average() {
    }

    @Test
    public void testIsComplete() {
        System.out.println("Testing isComplete");

        // Check that the empty object is 'not complete'
        assertFalse("empty object", empty.isComplete());

        // Check that the halfFull object is 'not complete'
        assertFalse("half full object", halfFull.isComplete());

        // Check that the empty object is 'not complete'
        assertTrue("full object", full.isComplete());
    }

    @Test
    public void testResetValues() {
        System.out.println("Testing resetValues");

        // Set the state of the 'full' object to zeroes
        full.resetValues();

        // Set expected results
        int expIntResult = 0;
        double expDoubleResult = 0.0;

        // Now check each attribute to test that the reset has worked
        assertEquals("credits", expIntResult, full.getTotalCredits());
        assertEquals("total", expDoubleResult, full.getTotalMarks(), 0.0);

        // Put the 'full' object back to its original state
        full.addModuleMark(120, 50.0);
    }

    @Test
    public void testAddModuleMark() {
        System.out.println("Testing addModuleMark");
        
        // Add a 10 credit module, mark 70%
        empty.addModuleMark(10, 70.0);
        assertEquals("Credits should be 10", 10, empty.getTotalCredits());
        assertEquals("Marks should be 70", 70.0, empty.getTotalMarks(), 0.0);

        // Add a 20 credit module, mark 40%
        empty.addModuleMark(20, 40.0);
        assertEquals("Credits should be 30", 30, empty.getTotalCredits());
        assertEquals("Marks should be 150", 150.0, empty.getTotalMarks(), 0.0);

        // Add a 40 credit module, mark 80%
        empty.addModuleMark(40, 80.0);
        assertEquals("Credits should be 70", 70, empty.getTotalCredits());
        assertEquals("Marks should be 470", 470.0, empty.getTotalMarks(), 0.0);
        
        // Restore the object to its empty state
        empty.resetValues();
    }

    @Test
    public void testCalculateAverageSoFar() {
        System.out.println("calculateAverageSoFar");

        // Test with no credits and no marks
        assertEquals("empty", 0.0, empty.calculateAverageSoFar(), 0.0);

        // Test with 120 credits all at 50%
        assertEquals("full @ 50%", 50.0, full.calculateAverageSoFar(), 0.0);

        // Test with 120 credits all at 100%
        full.resetValues();
        full.addModuleMark(120, 100.0);
        assertEquals("full @ 100%", 100.0, full.calculateAverageSoFar(), 0.0);

        // Test with 120 credits all at 43.92%
        full.resetValues();
        full.addModuleMark(120, 43.92);
        assertEquals("full @ 100%", 43.92, full.calculateAverageSoFar(), 0.0);
        full.resetValues();
        full.addModuleMark(120, 50.0);

        // Test with 60 credits at 50%
        assertEquals("60 credits @ 50%", 50.0, halfFull.calculateAverageSoFar(), 0.0);

        // Test with 60 credits at 100%
        halfFull.resetValues();
        halfFull.addModuleMark(60, 100.0);
        assertEquals("60 credits @ 100%", 100.0, halfFull.calculateAverageSoFar(), 0.0);

        // Test with 60 credits at 64.77%
        halfFull.resetValues();
        halfFull.addModuleMark(60, 64.77);
        assertEquals("60 credits @ 64.77%", 64.77, halfFull.calculateAverageSoFar(), 0.0);
        halfFull.resetValues();
        halfFull.addModuleMark(60, 50.0);

    }

    @Test
    public void testPredictClass() {
        System.out.println("predictClass");

        // Array to hold the stage 3 marks
        double[] marks = {0.00, 50.00, 50.00, 100.00, 39.99, 40.0,
            49.99, 50.0, 59.99, 60.0, 69.99, 70.0, 99.99, 35.67,
            44.22, 56.39, 64.00, 76.80};
        // Array of corresponding classifications with no stage 2 marks
        String[] expResult1 = {"No marks!", "Lower 2nd", 
            "Lower 2nd", "1st", "FAIL", "3rd", "3rd", "Lower 2nd", 
            "Lower 2nd", "Upper 2nd", "Upper 2nd", "1st", "1st", 
            "FAIL", "3rd", "Lower 2nd", "Upper 2nd", "1st"};
        // Array to hold the stage 2 marks
        double[] stage2 = {0.0, 50.0, 56.77, 70.00, 56.00, 49.53,
            52.78, 64.89, 47.82, 89.22, 70.00, 48.56, 100.00, 38.67,
            56.29, 77.00, 65.00, 83.00};
        // Array of corresponding classifications where there are stage 2 marks
        String[] expResult2 = {"No marks!", "Lower 2nd", "Lower 2nd",
            "1st",  "3rd", "3rd", "Lower 2nd", "Lower 2nd", "Lower 2nd",
            "Upper 2nd", "Upper 2nd", "Upper 2nd", "1st", "FAIL", "3rd",
            "Upper 2nd", "Upper 2nd", "1st"};


        // Run tests with no stage 2 average
        for (int count = 0; count < marks.length; count++) {
            full.resetValues();
            full.addModuleMark(120, marks[count]);
            assertEquals("120 credits, mark = " + marks[count], expResult1[count],
                    full.predictClass());
        }
// Run tests with stage 2 averages included
        for (int count = 0; count < marks.length; count++) {
            full.resetValues();
            full.addModuleMark(120, marks[count]);
            full.setStage2Average(stage2[count]);
            assertEquals("120 credits, mark = " + marks[count]
                    + " stage 2 = " + stage2[count],
                    expResult2[count], full.predictClass());
        }

        // Reset the object to standard
        full.resetValues();
        full.addModuleMark(120, 50.0);
        
        // Final check that no predcition is returned if the number of
        // credits is less than 120.
        assertEquals("No prediction for 60, i.e. < 120 credits", 
                "Insufficient credits", halfFull.predictClass());
        assertEquals("No prediction for 0, i.e. < 120 credits", 
                "Insufficient credits", empty.predictClass());
    }
    
    @Test
    public void testFullOperation() {
        int[] credits = {10, 10, 10, 20, 20, 40, 10};
        double[] marks = {60.6, 44.45, 80.0, 56.99, 62.3, 68.4, 59.11};
        double stage2 = 61.2;

        StageResults finalTest = new StageResults();

        // Add in the module marks and set the stage 2 average
        for (int count = 0; count < credits.length; count ++)
            finalTest.addModuleMark(credits[count], marks[count]);
        finalTest.setStage2Average(stage2);

        // Test the results
        assertEquals("stage 3 average", 63.03, finalTest.calculateAverageSoFar(), 0.0);
        assertEquals("predicted class", "Upper 2nd", finalTest.predictClass());
    }
}
