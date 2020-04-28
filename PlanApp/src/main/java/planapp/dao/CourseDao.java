package planapp.dao;

import java.util.List;
import planapp.domain.Course;

/**
 * Dao-interface for the class: Course
 */
public interface CourseDao {
    List<Course> getCourses();
    Course findCourse(String courseCode);
    boolean addCourse(String line);
    void addPrerequisites(String line);
}
