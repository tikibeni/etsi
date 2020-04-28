package planapp.dao;

import java.util.List;
import planapp.domain.Plan;

/**
 * Dao-interface for the class: Plan
 */
public interface PlanDao {
    Plan create(Plan plan) throws Exception;
    Plan findPlan(String planName);
    List<Plan> allPlans();
    boolean deletePlan(String planName) throws Exception;
    void updatePlans() throws Exception;
}
