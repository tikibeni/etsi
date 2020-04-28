package planapp.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import planapp.domain.Course;
import planapp.domain.Plan;


/**
 * Initializes and maintains plans.txt via config.file, return queries on object Plan, creates and deletes Plan-objects
 */
public class PlanFileDao implements PlanDao {
    private List<Plan> plans;
    private String file;
    
    // Reading Plan-information from given file for application usage
    public PlanFileDao(String file) throws Exception {
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

    /**
     * Method will deliver new Plan-object to be written in plans.txt
     * Used in Plan register function
     * 
     * @param plan  new Plan-object
     * @return plan  if delivered successfully
     * @throws Exception  if something went wrong during save()-method
     */
    @Override
    public Plan create(Plan plan) throws Exception {
        plans.add(plan);
        save();
        
        return plan;
    }
    
    /**
     * Method will find a name corresponding Plan-object within the application
     * Used in login function
     * 
     * @param planName - plan's name for search
     * @return Plan if found, null if not
     */
    @Override
    public Plan findPlan(String planName) {
        return plans.stream()
                .filter(p -> p.getPlanName()
                .equals(planName))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Returns all existing plans and plan information within the application.
     * 
     * @return List-object containing Plan-object(s)
     */
    @Override
    public List<Plan> allPlans() {
        return plans;
    }

    /**
     * Deleting of an existing Plan from the application. Will clear the corresponding plan from plans.txt
     * Used in Delete Plan -function
     * 
     * @param planName - plan name for deleting
     * @return true if deleted, false if something went wrong
     * @throws Exception if something went wrong during handling plans.txt
     */
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
    
    /**
     * CONSIDER MAKING SAVE()-METHOD PROTECTED AND DELETE THIS FROM EVERYWHERE.
     * Will trigger the save()-method. Initialized for testing purposes
     * @throws Exception if something went wrong during file handling
     */
    @Override
    public void updatePlans() throws Exception {
        save();
    }
}