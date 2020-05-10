
package planapp.domain;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlanServiceCourseTest {
    CourseDaoTest courseDao;
    PlanDaoTest planDao;
    PlanService planService;
        
    @Before
    public void setUp() {
        courseDao = new CourseDaoTest();
        planDao = new PlanDaoTest();
        Plan plan = new Plan("plan", "Test Plan");
        
        try {
            planDao.create(plan);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        planService = new PlanService(planDao, courseDao);
        planService.login(plan.getPlanName());
    }
    
    @Test
    public void courseAddingWorks() {
        planService.addCourse(courseDao.getCourses().get(0));
        
        assertEquals(1, planService.selectedCourses().size());
    }
    
    @Test
    public void courseFindingWorks() {
        assertEquals(courseDao.getCourses().get(0), planService.findCourse(courseDao.getCourses().get(0).getCourseCode()));
    }
    
    @Test
    public void courseRemovalWorks() {
        planService.addCourse(courseDao.getCourses().get(0));
        
        assertEquals(1, planService.selectedCourses().size());
        
        planService.removeCourse(courseDao.getCourses().get(0));
        
        assertTrue(planService.selectedCourses().isEmpty());
    }
    
    @Test
    public void courseRemovalFailSafe() {
        planService.addCourse(courseDao.getCourses().get(0));
        
        assertEquals(1, planService.selectedCourses().size());
        
        assertFalse(planService.removeCourse(new Course("TKTNON", "Non-Existing")));
    }
    
    @Test
    public void accessToAllExistingCourses() {
        List<Course> testCourses = planService.allCourses();
        
        assertFalse(testCourses.isEmpty());
    }
}
