package planapp.domain;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseCode;
    private String courseName;
    private List<Course> prerequisites;
    
    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.prerequisites = new ArrayList<>();
    }
    
    public String getCourseCode() {
        return this.courseCode;
    }
    
    public String getCourseName() {
        return this.courseName;
    }
    
    public List<Course> getPrerequisites() {
        return this.prerequisites;
    }
    
    public boolean addPrerequisite(Course course) {
        return prerequisites.add(course);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Course)) {
            return false;
        }
        
        Course c = (Course) obj;
        return courseCode.equals(c.courseCode);
    }
        
    @Override
    public String toString() {
        return this.courseCode + ": " + this.courseName;
    }
}
