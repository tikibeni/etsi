package planapp.dao;

import java.util.List;
import planapp.domain.Course;
import planapp.domain.Plan;

/**
 * DAO-interface for the class: Plan
 */
public interface PlanDao {
    Plan create(Plan plan) throws Exception;
    Plan findPlan(String planName);
    List<Plan> allPlans();
    void save() throws Exception;
    boolean deletePlan(String planName) throws Exception;
    boolean addPlan(String line);
    void addCourses(String line);    
    void deleteCourse(Course course) throws Exception;
}
