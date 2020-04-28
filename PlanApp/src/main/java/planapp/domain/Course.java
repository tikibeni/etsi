package planapp.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Application logic for object: Course
 */
public class Course {
    private String courseCode;
    private String courseName;
    private List<Course> prerequisites;
    
    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.prerequisites = new ArrayList<>();
    }
    
    /**
     * Returns Course-object's course code
     * 
     * @return String - course's code
     */
    public String getCourseCode() {
        return this.courseCode;
    }
    
    /**
     * Returns Course-object's course name
     * 
     * @return String - course's name
     */
    public String getCourseName() {
        return this.courseName;
    }
    
    /**
     * Returns Course-object's corresponding prerequisites.
     * 
     * @return List(Course) - course's list of prerequisites
     */
    public List<Course> getPrerequisites() {
        return this.prerequisites;
    }
    
    /**
     * Adds a prerequisite to object's corresponding list
     * 
     * @param course - new course
     * @return true if works, false otherwise
     */
    public boolean addPrerequisite(Course course) {
        return prerequisites.add(course);
    }
    
    /**
     * Compares selected object with this Course-object
     * 
     * @param obj - selected object for comparing
     * @return true if objects are same, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Course)) {
            return false;
        }
        
        Course c = (Course) obj;
        return courseCode.equals(c.courseCode);
    }
     
    /**
     * Returns the basic information regarding a Course-object.
     * 
     * @return String consisting of course code and course name
     */
    @Override
    public String toString() {
        return this.courseCode + ": " + this.courseName;
    }
}
