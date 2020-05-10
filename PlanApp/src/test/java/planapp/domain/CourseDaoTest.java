package planapp.domain;

import java.util.ArrayList;
import java.util.List;
import planapp.dao.CourseDao;

public class CourseDaoTest implements CourseDao {
    List<Course> courses = new ArrayList<>();
    Course latest = new Course("TKTTEST001", "Test Course");
    
    public CourseDaoTest() {
        courses.add(latest);
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
        String[] info = line.split(";");
        String code = info[0];
        String name = info[1];
        latest = new Course(code, name);
        return courses.add(latest);  
    }

    @Override
    public void addPrerequisites(String line) {
        String[] preInfo = line.split(";");
        String preCode = preInfo[0];
        String preName = preInfo[1];
        latest.addPrerequisite(new Course(preCode, preName));            
    }

    @Override
    public Course create(Course course) throws Exception {
        courses.add(course);
        return course;
    }

    @Override
    public void deleteCourse(Course course) throws Exception {
        if (courses.contains(course)) {
            courses.remove(course);
        }
    }

    @Override
    public void resetCourses() throws Exception {
        courses.clear();
        courses.add(new Course("TKTTEST001", "Test Course"));
    }
}
