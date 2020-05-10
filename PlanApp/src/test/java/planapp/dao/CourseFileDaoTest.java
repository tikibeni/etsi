package planapp.dao;

import java.io.File;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.apache.commons.io.FileUtils;

import planapp.domain.Course;

public class CourseFileDaoTest {
    
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    File courseFile;
    CourseDao courseDao;
        
    @Before
    public void setUp() throws Exception {
        courseFile = tempFolder.newFile("courses.txt");
        FileUtils.writeStringToFile(courseFile, "TKT10001;Johdatus tietojenkäsittelytieteeseen\nPREREQUISITES:\n\nTKT10002;Ohjelmoinnin perusteet\nPREREQUISITES:\n\nTKT10003;Ohjelmoinnin jatkokurssi\nPREREQUISITES:\nTKT10002;Ohjelmoinnin perusteet\n\n");
       
        courseDao = new CourseFileDao(courseFile.getAbsolutePath(), "Placeholder");
    }    
    
    @Test
    public void findsSpecificCourse() {
        Course template = new Course("TKT10001", "Johdatus tietojenkäsittelytieteeseen");
        
        assertEquals(template, courseDao.findCourse("TKT10001"));
    }
    
    @Test
    public void informationOnExistThroughList() {
        List<Course> courses = courseDao.getCourses();
        Course c = new Course("TKT10003", "Ohjelmoinnin jatkokurssi");
        
        assertTrue(courses.contains(c));
    }
    
    @Test
    public void findReturnsNullIfNonExistingObject() {
        assertEquals(null, courseDao.findCourse("TKTNONEXIST"));
    }    
    
    @Test
    public void canCreateAndDeleteNew() {
        try {
            courseDao.create(new Course("TKT10005", "Tito"));
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        
        assertTrue(courseDao.getCourses().size() == 4);
        
        try {
            courseDao.deleteCourse(new Course("TKT10005", "Tito"));
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        
        assertEquals(3, courseDao.getCourses().size());
    }
    
    @Test
    public void resetFunctionDAOResponsibility() {
        assertTrue(courseDao.getCourses().size() == 3);
        
        try {
            courseDao.resetCourses();
        } catch (Exception e) {
            System.out.println("Testing went wrong");
        }
        
        assertTrue(courseDao.getCourses().size() == 3);
    }
    
    
    @After
    public void tearDown() {
        courseFile.delete();
    }
}
