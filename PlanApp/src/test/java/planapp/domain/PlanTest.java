package planapp.domain;

import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
    
    @Test
    public void planCourses() {
        Plan p = new Plan("Plan", "Confused Fresher");
        p.addCourse(new Course("TKTTEST", "Course Testing"));
        
        List<Course> pCourses = p.getCourses();
        
        assertEquals("TKTTEST: Course Testing", pCourses.get(0).toString());
    }
    
    @Test
    public void planCourseRemoval() {
        Plan p = new Plan("Plan", "Confused Fresher");
        Course c = new Course("TKTTEST", "Course Testing");
        p.addCourse(c);
        p.removeCourse(c);
        
        assertTrue(p.getCourses().isEmpty());
    }
    
    @Test
    public void cantRemoveNonContainedCourse() {
        Plan p = new Plan("Plan", "Confused Fresher");
        
        assertFalse(p.removeCourse(new Course("TKTTEST", "Course Testing")));
    }
}
