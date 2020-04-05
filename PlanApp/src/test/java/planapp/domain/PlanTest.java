package planapp.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PlanTest {    
    
    @Test
    public void notSamePlan() {
        Plan test = new Plan("Plan", "Confused Fresher");
        Plan test1 = new Plan("DiffPlan", "A Student of N years");
        
        assertFalse(test.equals(test1));
    }

    @Test
    public void differentObjects(){
        Plan p = new Plan("Plan", "Confused Fresher");
        Object x = new Object();
        assertFalse(p.equals(x));
    }

    @Test
    public void classWorksCorrectly() {
        Plan test = new Plan("Plan", "Confused Fresher");
        assertEquals("Plan: Plan, by:Confused Fresher", test.toString());
    }
}
