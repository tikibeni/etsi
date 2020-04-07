package planapp.domain;

import java.util.List;
import planapp.dao.CourseDao;
import planapp.dao.PlanDao;

public class PlanService {
    private PlanDao planDao;
    // CURRENTLY UNUSED:
    private CourseDao courseDao;
    private Plan currentPlan;
    
    public PlanService(PlanDao planDao, CourseDao courseDao) {
        this.planDao = planDao;
        this.courseDao = courseDao;
    }
    
    public boolean login(String planName) {
        Plan plan = planDao.findPlan(planName);
        if (plan == null) {
            return false;
        }
        
        this.currentPlan = plan;
        
        return true;
    }
    
    public void logout() {
        currentPlan = null;
    }
    
    public String thisPlansAuthor() {
        return currentPlan.getUserName();
    }
    
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
    
    public boolean deletePlan() {
        try {
            planDao.deletePlan(currentPlan.getPlanName());
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public boolean addCourse(Course course) {
        if (currentPlan.addCourse(course)) {
            try {
                planDao.updatePlans();
            } catch (Exception e) {
                return false;
            }
            
            return true;
        }
        
        return false;
    }
    
    public List<Course> allCourses(){
        return courseDao.getCourses();
    }
    
    public boolean removeCourse(Course course) {
        if (currentPlan.removeCourse(course)) {
            try {
                planDao.updatePlans();                
            } catch (Exception e) {
                return false;
            }
            
            return true;
        }
        
        return false;
    }    
}
