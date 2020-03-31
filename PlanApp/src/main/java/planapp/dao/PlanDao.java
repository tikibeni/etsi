package planapp.dao;

import java.util.List;
import planapp.domain.Plan;

public interface PlanDao {
    
    Plan create(Plan plan) throws Exception;
    Plan findPlan(String planName);
    List<Plan> allPlans();
    boolean deletePlan(String planName) throws Exception;
    
}
