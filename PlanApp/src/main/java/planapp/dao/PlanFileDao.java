package planapp.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import planapp.domain.Plan;


public class PlanFileDao implements PlanDao {
    
    private List<Plan> plans;
    private String file;
    
    public PlanFileDao(String file) throws Exception {
        plans = new ArrayList<>();
        this.file = file;
        
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                Plan p = new Plan(parts[0], parts[1]);
                plans.add(p);
            }
            
        } catch (FileNotFoundException e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }
    
    private void save() throws Exception {
        
        try (FileWriter writer = new FileWriter(new File(file))) {
            
            for (Plan p: plans) {
                writer.write(p.getPlanName() + ";" + p.getUserName() + "\n");
            }
            
        }
    }

    @Override
    public Plan create(Plan plan) throws Exception {
        plans.add(plan);
        save();
        
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
        for (Plan p : plans) {
            if (p.getPlanName().equals(planName)) {
                plans.remove(p);
                save();
                return true;
            }
        }
        
        return false;
    }
    
}
