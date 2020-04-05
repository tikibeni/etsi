package planapp.domain;

import planapp.dao.PlanDao;

public class PlanService {
    private PlanDao planDao;
    private Plan currentPlan;
    
    public PlanService(PlanDao planDao) {
        this.planDao = planDao;
    }
    
    public boolean login(String planName) {
        Plan plan = planDao.findPlan(planName);
        if (plan == null) {
            return false;
        }
        
        this.currentPlan = plan;
        
        return true;
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
}
