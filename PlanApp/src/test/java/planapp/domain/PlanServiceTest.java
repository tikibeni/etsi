
package planapp.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlanServiceTest {
    
    TestPlanDao planDao;
    PlanService planService;
    
    @Before
    public void setUp() {
        planDao = new TestPlanDao();
        planService = new PlanService(planDao);
    }
    
    @Test
    public void loginWorksCorrectly1(){
        assertTrue(planService.login("Test"));
    }
    
    @Test
    public void loginWorksCorrectly2(){
        assertFalse(planService.login("Test2"));
    }
    
    @Test
    public void correctPlanAuthor(){
        planService.login("Test");
        assertEquals(planService.thisPlansAuthor(), "Test Test");
    }
    
    @Test
    public void createsPlanCorrectly1(){
        assertFalse(planService.createPlan("Test", "Test Test"));
    }
    
    @Test
    public void createsPlanCorrectly2() {
        assertTrue(planService.createPlan("Testt", "Tester Test"));
    }
    
    @Test
    public void deletesPlanCorrectly1(){
        planService.createPlan("Delete", "test");
        planService.login("Delete");
        assertTrue(planService.deletePlan());
    }
    
    
}
