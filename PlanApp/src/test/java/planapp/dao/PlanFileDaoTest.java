package planapp.dao;

import java.io.File;
import java.io.FileWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import planapp.domain.Course;
import planapp.domain.Plan;
import planapp.domain.CourseDaoTest;

public class PlanFileDaoTest {
    
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    
    File planFile;
    PlanDao planDao;
    CourseDao courseDao;
    
    @Before
    public void setUp() throws Exception {
        planFile = tempFolder.newFile("test_plans.txt");
        courseDao = new CourseDaoTest();
        
        try (FileWriter file = new FileWriter(planFile.getAbsolutePath())) {
            file.write("freshplan;Fresher\nCOURSES:\nTKT10001;JTKT\nTKT10002;Ohpe\n\n");
        }
        
        planDao = new PlanFileDao(planFile.getAbsolutePath());
    }
    
    @Test
    public void registeringWorks() throws Exception {
        Plan newPlan = new Plan("testplan", "tester");
        
        assertEquals(newPlan, planDao.create(newPlan));
    }
    
    @Test
    public void planFindingWorksThroughCreation() throws Exception {
        Plan newPlan = new Plan("testplan", "tester");
        
        assertEquals(newPlan, planDao.create(newPlan));        
        assertEquals(newPlan, planDao.findPlan("testplan"));
    }
    
    @Test
    public void planFindingReturnsNullIfNonExisting() throws Exception {
        assertEquals(null, planDao.findPlan("testplan"));
    }
    
    @Test
    public void planDeletionIsPossible() throws Exception {
        Plan newPlan = new Plan("testplan", "tester");
        
        assertEquals(newPlan, planDao.create(newPlan));
        
        assertTrue(planDao.deletePlan("testplan"));
    }
    
    @Test
    public void planDeletionNotPossibleIfNonExisting() throws Exception {
        assertFalse(planDao.deletePlan("testplan"));
    }
    
    @Test
    public void findsPlansCoursesCorrectly() throws Exception {
        assertFalse(planDao.findPlan("freshplan").getCourses().isEmpty());
        
        
        Plan newPlan = new Plan("Test", "Tester Test");
        newPlan.addCourse(courseDao.getCourses().get(0));
        
        planDao.create(newPlan);
        assertEquals(1, planDao.findPlan("Test").getCourses().size());
    }
    
    @Test
    public void accessToExistingPlans() throws Exception {
        assertEquals(1, planDao.allPlans().size());
        
        Plan newPlan = new Plan("Test", "Tester Test");
        planDao.create(newPlan);
        
        assertEquals(2, planDao.allPlans().size());
    }
    
    @Test
    public void courseDeletionFromPlan() throws Exception {
        planDao.deleteCourse(new Course("TKT10001", "JTKT"));
        
        assertTrue(planDao.findPlan("freshplan").getCourses().size() == 1);
    }
    
    @After
    public void tearDown() {
        planFile.delete();
    }   
}
