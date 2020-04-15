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
    // TODO
    
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    
    File courseFile;
    CourseDao courseDao;
        
    @Before
    public void setUp() throws Exception {
        courseFile = tempFolder.newFile("test_courses.txt");
        String courseInfo = "TKT10001;Johdatus tietojenkäsittelytieteeseen\nPREREQUISITES:\n\nTKT10002;Ohjelmoinnin perusteet\nPREREQUISITES:\n\nTKT10003;Ohjelmoinnin jatkokurssi\nPREREQUISITES:\nTKT10002;Ohjelmoinnin perusteet\n\n";
       
        courseDao = new CourseFileDao(courseFile.getAbsolutePath(), courseInfo);
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
