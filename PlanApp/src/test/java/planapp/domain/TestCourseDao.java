package planapp.domain;

import java.util.ArrayList;
import java.util.List;
import planapp.dao.CourseDao;

public class TestCourseDao implements CourseDao {
    
    List<Course> courses = new ArrayList<>();
    
    public TestCourseDao() {
        courses.add(new Course("TKTTEST001", "Test Course"));
    }

    @Override
    public List<Course> getCourses() {
        return courses;
    }    
}