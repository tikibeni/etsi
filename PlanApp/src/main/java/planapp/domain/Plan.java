package planapp.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Application logic for object: Plan
 */
public class Plan {
    private String planName;
    private String userName;
    private List<Course> courses;
    
    public Plan(String planName, String userName) {
        this.planName = planName;
        this.userName = userName;
        this.courses = new ArrayList<>();
    }
    
    /**
     * Returns this Plan-object's plan name.
     * 
     * @return String - plan's name
     */
    public String getPlanName() {
        return this.planName;
    }
    
    /**
     * Returns this Plan-object's author
     * 
     * @return String - plan's author
     */
    public String getUserName() {
        return this.userName;
    }
    
    /**
     * Returns this plan's selected courses as a list
     * 
     * @return List(Course) - plan's selected courses
     */
    public List<Course> getCourses() {
        return this.courses;
    }
    
    /**
     * Adds a new course to this Plan-object's list of selected courses.
     * 
     * @param course - new course for selected courses -list
     * @return true if successfully added, otherwise false
     */
    public boolean addCourse(Course course) {
        return this.courses.add(course);
    }
    
    /**
     * Removes a corresponding course from Plan-object's list of selected courses.
     * 
     * @param course - courseinfo for removing from selected ones
     * @return true if removed successfully, otherwise false
     */
    public boolean removeCourse(Course course) {
        if (!this.courses.contains(course)) {
            return false;
        }
        
        return this.courses.remove(course);
    }
    
    /**
     * Compares selected object to this Plan-object.
     * 
     * @param obj - selected object for comparing
     * @return true if objects are the same, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Plan)) {
            return false;
        }
        
        Plan p = (Plan) obj;
        return planName.equals(p.planName);
    }
    
    /**
     * Returns the primary information of this Plan-object
     * 
     * @return String - combination of information
     */
    @Override
    public String toString() {
        return "Plan: " + this.planName + ", by:" + this.userName;
    }
}
