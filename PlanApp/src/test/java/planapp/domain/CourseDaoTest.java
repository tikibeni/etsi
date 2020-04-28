package planapp.domain;

import java.util.ArrayList;
import java.util.List;
import planapp.dao.CourseDao;

public class CourseDaoTest implements CourseDao {
    // TODO
    List<Course> courses = new ArrayList<>();
    
    public CourseDaoTest() {
        courses.add(new Course("TKTTEST001", "Test Course"));
    }

    @Override
    public List<Course> getCourses() {
        return courses;
    }    

    @Override
    public Course findCourse(String courseCode) {
        for (Course c : courses) {
            if (c.getCourseCode().equals(courseCode)) {
                return c;
            }
        }
        
        return null;
    }

    @Override
    public boolean addCourse(String line) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addPrerequisites(String line) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
