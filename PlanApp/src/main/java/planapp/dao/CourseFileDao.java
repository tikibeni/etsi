package planapp.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import planapp.domain.Course;

/**
 * Initializes and maintains courses.txt via config.file and returns queries on object Course
 */
public class CourseFileDao implements CourseDao {
    public List<Course> courses;
    private String courseFile;

    // Reading info from given coursefile for application usage during startup
    public CourseFileDao(String courseFile, String courseInfo) throws Exception {
        this.courses = new ArrayList<>();
        this.courseFile = courseFile;
        try {
            initialize();
        } catch (FileNotFoundException e) {
            String[] lines = courseInfo.split("\n");
            try (FileWriter writer = new FileWriter(new File(courseFile))) {
                for (String line : lines) {
                    writer.write(line + "\n");
                }
                writer.close();
                initialize();
            } catch (Exception ee) {
                System.out.println("Error while initializing file: courses.txt. Reimplement course.file and try again.");
            }
        }
    }
    
    // If courses.txt doesn't exist, initialize via config.file
    private void initialize() throws FileNotFoundException {
        boolean prerequisitesLine = false;
        Scanner reader = new Scanner(new File(courseFile));
        Course latest = new Course("Placeholder", "Placeholder");
            
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
                
            // Handling the course's prerequisites part
            if (line.equals("PREREQUISITES:")) {
                prerequisitesLine = true;
            }
            if (prerequisitesLine && reader.hasNextLine()) {
                line = reader.nextLine();
                while (!line.equals("")) {
                    String[] preInfo = line.split(";");
                    String preCode = preInfo[0];
                    String preName = preInfo[1];
                    latest.addPrerequisite(new Course(preCode, preName));
                        
                    if (reader.hasNext()) {
                        line = reader.nextLine();   
                    } else {
                        break;
                    }
                }
                prerequisitesLine = false;
                continue;
            }
                
            // Reading existing courses into application
            if (!line.equals("")) {
                String[] info = line.split(";");
                String code = info[0];
                String name = info[1];
                Course course = new Course(code, name);
                latest = course;
                courses.add(course); 
            }
        }
    }
    
    /**
     * Returns all existing courses and course information within the application.
     * 
     * @return List-object containing Course-object(s)
     */
    @Override
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Searches for code's corresponding course within the application via comparing.
     * 
     * @param courseCode - course code for search
     * 
     * @return Course if found, null if not found
     */
    @Override
    public Course findCourse(String courseCode) {
        for (Course c : courses) {
            if (c.getCourseCode().equals(courseCode)) {
                return c;
            }
        }
        
        return null;
    }
}
