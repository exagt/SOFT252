/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ehughes2
 */
public class StageResultsTest {
    private StageResults empty;     // will have no credits and no marks
    private StageResults full;      // will have 120 credits and marks
    private StageResults halfFull;  // will have 60 credits and some marks
            
    public StageResultsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        // empty - object that starts with default values
        empty = new StageResults();
        
        // full - object with 120 credits worth of marks but
        // no initial stage2Average
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
        //fail("Test not yet implemented");
        System.out.println("Testing isComplete");
        
        // Check that the empty object is 'not complete'
        assertFalse("empty object", empty.isComplete());
        
        // Check that the full object is 'not complete'
        assertTrue("full object", full.isComplete());
        
        // Check that the halfFull object is 'not complete'
        assertFalse("halfFull object", halfFull.isComplete());
    }

    @Test
    public void testResetValues() {
        //fail("Test not yet implemented");
        System.out.println("Testing resetValues");
        
        // Set the state of the 'full' object to zeroes
        full.resetValues();
        
        // Set expected results
        int expIntResult = 0;
        double expDoubleResult = 0.0;
        
        // Now check each attrivute to test that the reset has worked
        assertEquals("credits", expIntResult, full.getTotalCredits());
        assertEquals("total", expDoubleResult, full.getTotalMarks(), 0.0);
        
        // Put the 'full' object back to its original state
        full.addModuleMark(120, 50.0);
    }

    @Test
    public void testAddModuleMark() {
        //fail("Test not yet implemented");
        System.out.println("Testing addModuleMark");
        
        // Set expected results
        int expIntResult = 70;
        double expDoubleResult = 350.0;
        
        // Add the 10, 20 and 40 credit modules to the 'empty' object
        empty.addModuleMark(70, 50);
        /*
        empty.addModuleMark(10, 50.0);
        empty.addModuleMark(20, 50.0);
        empty.addModuleMark(40, 50.0);
        */
        
        // Check that each module mark was added correctly
        assertEquals("credits", expIntResult, empty.getTotalCredits());
        assertEquals("total", expDoubleResult, empty.getTotalMarks(), 0.0);
        
        // Set the state of the 'empty' object to zeroes
        empty.resetValues();
    }

    @Test
    public void testCalculateAverageSoFar() {
        //fail("Test not yet implemented");
        System.out.println("Testing calculateAverageSoFar");
        
        // Test with 120 credits at 43.92%
        full.resetValues();
        full.addModuleMark(120, 43.92);
        assertEquals("full @ 43.92%", 43.92, full.calculateAverageSoFar(), 0.0);
        // Return full back to regular values
        full.resetValues();
        full.addModuleMark(120, 50.0);
        
        // Test with 60 credits at 64.77%
        halfFull.resetValues();
        halfFull.addModuleMark(60, 64.77);
        assertEquals("halfFull @ 64.77%", 64.77, halfFull.calculateAverageSoFar(), 0.0);
        // Return halfFull back to regular values
        halfFull.resetValues();
        halfFull.addModuleMark(60, 50);
        
        // Test with 120 credits at 100%
        full.resetValues();
        full.addModuleMark(120, 100);
        assertEquals("full @ 100%", 100, full.calculateAverageSoFar(), 0.0);
        // Return full back to regular values
        full.resetValues();
        full.addModuleMark(120, 50);
    }

    @Test
    public void testPredictClass() {
        //fail("Test not yet implemented");
        System.out.println("Testing predictClass");
        
        // Array to hold stage 3 marks
        double[] marks = {0.00, 50.00, 50.00, 100.00, 39.99, 40.0, 49.99, 50.00,
            59.99, 60.00, 69.99, 70.0, 99.99, 35.67, 44.22, 56.39, 64.00, 
            76.80};
        // Array of corresponding classifications with no stage 2 marks
        String[] expResult1 = {"No marks!", "Lower 2nd", "Lower 2nd", "1st", 
            "FAIL", "3rd", "3rd", "Lower 2nd", "Lower 2nd", "Upper 2nd", 
            "Upper 2nd", "1st", "1st", "FAIL", "3rd", "Lower 2nd", "Upper 2nd",
            "1st"};
        // Array of Stage 2 average marks
        double[] stage2 = {0.00, 50.00, 56.77, 70.00, 56.00, 49.53, 52.78, 
        64.89, 47.82, 89.22, 70.00, 48.56, 100.00, 38.67, 56.29, 77.00, 65.00,
        83.00};
        // Array of corresponding classifications with stage 2 marks
        String[] expResult2 = {"No marks!", "Lower 2nd", "Lower 2nd", "1st", 
        "3rd", "3rd", "Lower 2nd", "Lower 2nd", "Lower 2nd", "Upper 2nd", 
        "Upper 2nd", "Upper 2nd", "1st", "FAIL", "3rd", "Upper 2nd", 
        "Upper 2nd", "1st"};
        
        // Run tests with no stage 2 average
        for (int count = 0; count < marks.length; count++) {
            full.resetValues();
            full.addModuleMark(120, marks[count]);
            assertEquals("120 credits, mark = " + marks[count], 
                    expResult1[count], full.predictClass());
        }
        
        // Run tests with stage 2 average
        for (int count = 0; count < marks.length; count++) {
            full.resetValues();
            full.addModuleMark(120, marks[count]);
            full.setStage2Average(stage2[count]);
            assertEquals("120 credits, mark = " + marks[count] + " stage 2 = " +
                    stage2[count], expResult2[count], full.predictClass());
        }
        
        // Reset the 'full' object
        full.resetValues();
        full.addModuleMark(120, 50.00);
        
        // Final check to make sure no prediction is returned if <120 credits
        assertEquals("No prediction for 60 credits.", "Insufficient credits",
                halfFull.predictClass());
        assertEquals("No prediction for 0 credits.", "Insufficient credits",
                empty.predictClass());
        
        //fuck knows what's wrong with this test method
    }
    
    @Test
    public void testFullOperation() {
        int[] credits = {10, 10, 10, 20, 20, 40, 10};
        double[] marks = {60.6, 44.45, 80.0, 56.99, 62.3, 68.4, 59.1};
        double stage2 = 61.2;
        
        StageResults finalTest = new StageResults();
        
        // Add in the module marks and set the stage 2 average
        for (int count = 0; count < credits.length; count++) {
            finalTest.addModuleMark(credits[count], marks[count]);
        }
        finalTest.setStage2Average(stage2);
        
        // Test the results
        assertEquals("stage 3 average", 63.03, finalTest.calculateAverageSoFar(), 0.0);
        assertEquals("predicated class ", "Upper 2nd", finalTest.predictClass());
    }
    
}