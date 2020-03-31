package planapp.domain;

import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class PlanTest {    
    
    @Test
    public void notEqualPlans(){
        Plan test = new Plan("Plan", "Confused fresher");
        Plan test1 = new Plan("Plan1", "Confused fresher");
        
        assertFalse(test.equals(test1));
    }
}
