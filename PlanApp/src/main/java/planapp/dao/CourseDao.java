package planapp.dao;

import java.util.List;
import planapp.domain.Course;

/**
 * DAO-interface for the class: Course
 */
public interface CourseDao {
    Course create(Course course) throws Exception;
    Course findCourse(String courseCode);
    List<Course> getCourses();
    boolean addCourse(String line);
    void addPrerequisites(String line);
    void deleteCourse(Course course) throws Exception;
    void resetCourses() throws Exception;
}
