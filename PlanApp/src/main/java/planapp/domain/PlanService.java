package planapp.domain;

import java.util.List;
import planapp.dao.CourseDao;
import planapp.dao.PlanDao;

/**
 * Application logic for the basic functions in the application
 */
public class PlanService {
    private PlanDao planDao;
    private CourseDao courseDao;
    private Plan currentPlan;
    
    public PlanService(PlanDao planDao, CourseDao courseDao) {
        this.planDao = planDao;
        this.courseDao = courseDao;
    }
    
    /**
     * App's login function. Uses planDao to find name corresponding plan from plans.txt
     * If plan is found, logs said plan in via setting it as the value of 'currentPlan'
     * 
     * @param planName - name for login
     * @return true if login success, false otherwise
     */
    public boolean login(String planName) {
        Plan plan = planDao.findPlan(planName);
        if (plan == null) {
            return false;
        }
        
        this.currentPlan = plan;
        
        return true;
    }
    
    /**
     * Resets the value of 'currentPlan' to null to cause a logout.
     */
    public void logout() {
        currentPlan = null;
    }
    
    /**
     * Uses the currentPlan-object to return the name of current author.
     * 
     * @return String - plan's author
     */
    public String thisPlansAuthor() {
        return currentPlan.getUserName();
    }
    
    /**
     * Returns a list of selected courses via current plan's method getCourses()
     * 
     * @return List(Course) - selected courses
     */
    public List<Course> selectedCourses() {
        return currentPlan.getCourses();
    }
    
    /**
     * Application's register function. Uses planDao to initialize new Plan-object into app.
     * 
     * @param planName - name for plan creating
     * @param userName - author for plan creating
     * @return true if plan registered successfully, false otherwise
     */
    public boolean createPlan(String planName, String userName) {
        if (planDao.findPlan(planName) != null) {
            return false;
        }
        
        Plan plan = new Plan(planName, userName);
        try {
            planDao.create(plan);
        } catch (Exception e) {
            return false;
        }
        
        
        return true;
    }
    
    /**
     * Application's delete plan function. Uses planDao to find and remove selected plan.
     * 
     * @return true if found and deleted, false otherwise
     */
    public boolean deletePlan() {
        try {
            planDao.deletePlan(currentPlan.getPlanName());
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    /**
     * Application's select course function. Uses planDao to save selected courses within plans.txt
     * 
     * @param course - object for adding a new course to plan
     * @return true if added successfully, false otherwise
     */
    public boolean addCourse(Course course) {
        if (currentPlan.addCourse(course)) {
            try {
                planDao.save();
            } catch (Exception e) {
                return false;
            }
            
            return true;
        }
        
        return false;
    }
    
    /**
     * Application's course pool function. Uses courseDao to display all possible courses via UI.
     * 
     * @return List(Course) - all existing courses
     */
    public List<Course> allCourses() {
        return courseDao.getCourses();
    }
    
    /**
     * Uses courseDao to find selected course from course database.
     * 
     * @param code - course code for searching
     * @return Course - found course
     */
    public Course findCourse(String code) {
        return courseDao.findCourse(code);
    }
    
    /**
     * Removes a course from current plan's selected courses via planDao.
     * 
     * @param course - object for removing a course from selected ones
     * @return true if removed successfully, false otherwise.
     */
    public boolean removeCourse(Course course) {
        if (currentPlan.removeCourse(course)) {
            try {
                planDao.save();                
            } catch (Exception e) {
                return false;
            }
            
            return true;
        }
        
        return false;
    }    
}
