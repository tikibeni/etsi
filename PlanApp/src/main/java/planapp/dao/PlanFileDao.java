package planapp.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import planapp.domain.Course;
import planapp.domain.Plan;

public class PlanFileDao implements PlanDao {
    private List<Plan> plans;
    private String file;
    
    // Reading Plan-information from given file for application usage
    public PlanFileDao(String file, CourseDao courses) throws Exception {
        plans = new ArrayList<>();
        this.file = file;
        
        try {
            boolean courseLines = false;
            Scanner reader = new Scanner(new File(file));
            Plan latest = new Plan("Placeholder", "Placeholder");
            
            // Plan's courseline handling
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.equals("COURSES:")) {
                    courseLines = true;
                }
                if (courseLines && reader.hasNextLine()) {
                    line = reader.nextLine();
                    while (!line.equals("")) {
                        String[] courseLine = line.split(";");
                        String courseCode = courseLine[0];
                        String courseName = courseLine[1];
                        latest.addCourse(new Course(courseCode, courseName));
                        
                        if (reader.hasNext()) {
                            line = reader.nextLine();
                        } else {
                            break;
                        }
                    }
                    
                    courseLines = false;
                    continue;
                }
                
                // Plan core's handling
                if (!line.equals("")) {
                    String[] parts = line.split(";");
                    Plan p = new Plan(parts[0], parts[1]);
                    latest = p;
                    plans.add(p);
                }
                
            }
        // Initialize the plan.txt if non-existing    
        } catch (FileNotFoundException e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }
    
    // Writing plans into file
    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Plan p: plans) {
                // Writing the plan's basic info into file
                writer.write(p.getPlanName() + ";" + p.getUserName() + "\n");
                
                // Writing the plan's selected courses into file if selected
                writer.write("COURSES:");
                writer.write("\n");
                if (!p.getCourses().isEmpty()) {
                    for (Course c : p.getCourses()) {
                        writer.write(c.getCourseCode() + ";" + c.getCourseName() + "\n");
                    }
                }
                writer.write("\n");
            }
        }
    }

    // Plan creating function for register
    @Override
    public Plan create(Plan plan) throws Exception {
        plans.add(plan);
        save();
        
        return plan;
    }
    
    // Plan finding function for login
    @Override
    public Plan findPlan(String planName) {
        return plans.stream()
                .filter(p -> p.getPlanName()
                .equals(planName))
                .findFirst()
                .orElse(null);
    }
    
    // All existing plans as a list for application to use
    @Override
    public List<Plan> allPlans() {
        return plans;
    }

    // Deletion of a plan
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
    
    @Override
    public void updatePlans() throws Exception {
        save();
    }
}