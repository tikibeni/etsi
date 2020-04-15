
package planapp.dao;

import java.io.File;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import planapp.domain.Course;

public class CourseFileDaoTest {
    
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    
    File courseFile;
    CourseDao courseDao;
        
    @Before
    public void setUp() throws Exception {
        courseFile = tempFolder.newFile("test_courses.txt");
        
        courseDao = new CourseFileDao(courseFile.getAbsolutePath(),"TKT20001;Tira\nPREREQUISITES:\nTKT10002;Ohpe\nTKT10003;Ohja\n");
    }
    
    @Test
    public void returnsExistingCourses() {
        assertFalse(courseDao.getCourses().isEmpty());
    }
    
    @Test
    public void findsCoursesWithCourseCode() {
        Course c = courseDao.findCourse("TKT20001");
        
        assertEquals("Tira", c.getCourseName());
    }
    
    @Test
    public void findsCoursePrerequisites() {
        List<Course> prerequisites = courseDao.findCourse("TKT20001").getPrerequisites();
        
        assertEquals(2, prerequisites.size());
    }
    
    @Test
    public void findReturnsNullIfNonExistingObject() {
        assertEquals(null, courseDao.findCourse("TKTNONEXIST"));
    }
    
    @After
    public void tearDown() {
        courseFile.delete();
    }
}
