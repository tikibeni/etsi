package planapp.dao;

import java.util.List;
import planapp.domain.Course;

public interface CourseDao {
    List<Course> getCourses();
    Course findCourse(String courseCode);
}
