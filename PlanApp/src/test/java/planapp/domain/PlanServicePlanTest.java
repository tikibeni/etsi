package planapp.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlanServicePlanTest {
    
    PlanDaoTest planDao;
    CourseDaoTest courseDao;
    PlanService planService;
    
    @Before
    public void setUp() {
        planDao = new PlanDaoTest();
        courseDao = new CourseDaoTest();
        planService = new PlanService(planDao, courseDao);
    }
    
    @Test
    public void loginWorksCorrectly1() {
        assertTrue(planService.login("Test"));
    }
    
    @Test
    public void loginWorksCorrectly2() {
        assertFalse(planService.login("Test2"));
    }
    
    @Test
    public void correctPlanAuthor() {
        planService.login("Test");
        assertEquals(planService.thisPlansAuthor(), "Test Test");
    }

    @Test
    public void createsPlanCorrectly1() {
        assertFalse(planService.createPlan("Test", "Test Test"));
    }
    
    @Test
    public void createsPlanCorrectly2() {
        assertTrue(planService.createPlan("Testt", "Tester Test"));
    }

    @Test
    public void deletesPlanCorrectly1() {
        planService.createPlan("Delete", "test");
        planService.login("Delete");
        assertTrue(planService.deletePlan());
    }

    @Test
    public void cantDeleteNonExisting() {
        assertFalse(planService.deletePlan());
    }
    
    @Test
    public void logoutWorksCorrectlyThroughDeletion() {
        planService.createPlan("Testing", "Logout");
        planService.login("Testing");
        planService.logout();
        
        assertFalse(planService.deletePlan());
    }
}