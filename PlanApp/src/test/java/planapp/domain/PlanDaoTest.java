package planapp.domain;

import java.util.ArrayList;
import java.util.List;
import planapp.dao.PlanDao;

public class PlanDaoTest implements PlanDao {
    // TODO
    List<Plan> plans = new ArrayList<>();
    
    public PlanDaoTest(){
        plans.add(new Plan("Test", "Test Test"));
    }

    @Override
    public Plan create(Plan plan) throws Exception {
        plans.add(plan);
        return plan;
    }

    @Override
    public Plan findPlan(String planName) {
        return plans.stream()
                .filter(p -> p.getPlanName()
                .equals(planName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Plan> allPlans() {
        return plans;
    }

    @Override
    public boolean deletePlan(String planName) throws Exception {
        for(Plan p : plans){
            if(p.getPlanName().equals(planName)){
                plans.remove(p);
                return true;
            }
        }
        
        return false;
    }

    @Override
    public void save() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addPlan(String line) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addCourses(String line) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
