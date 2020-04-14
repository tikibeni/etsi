package planapp.domain;

import java.util.ArrayList;
import java.util.List;

public class Plan {
    private String planName;
    private String userName;
    private List<Course> courses;
    
    public Plan(String planName, String userName) {
        this.planName = planName;
        this.userName = userName;
        this.courses = new ArrayList<>();
    }
    
    public String getPlanName() {
        return this.planName;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public List<Course> getCourses() {
        return this.courses;
    }
    
    public boolean addCourse(Course course) {
        return this.courses.add(course);
    }
    
    public boolean removeCourse(Course course) {
        if (!this.courses.contains(course)) {
            return false;
        }
        
        return this.courses.remove(course);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Plan)) {
            return false;
        }
        
        Plan p = (Plan) obj;
        return planName.equals(p.planName);
    }
    
    @Override
    public String toString() {
        return "Plan: " + this.planName + ", by:" + this.userName;
    }
}
