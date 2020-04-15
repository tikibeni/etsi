package planapp.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import planapp.domain.Course;

public class CourseFileDao implements CourseDao {
    public List<Course> courses;
    private String courseFile;

    // Reading info from given coursefile for application usage
    public CourseFileDao(String courseFile, String courseInfo) throws Exception {
        this.courses = new ArrayList<>();
        this.courseFile = courseFile;
        try {
            initialize(courseFile);
        } catch (FileNotFoundException e) {
            String[] lines = courseInfo.split("\n");
            try (FileWriter writer = new FileWriter(new File(courseFile))) {
                for (String line : lines) {
                    writer.write(line + "\n");
                }
                writer.close();
                initialize(courseFile);
            } catch (Exception ee) {
                System.out.println("Error while initializing file: courses.txt");
            }
        }
    }
    
    private void initialize(String courseFile) throws FileNotFoundException {
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
                
            // Creating existing courses
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
    
    // Creating new courses to file (CURRENTLY UNUSED)
    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(courseFile))) {
            for (Course c : courses) {
                // Writing the course's basic info
                writer.write(c.getCourseCode() + ";" + c.getCourseName() + "\n");
                
                // Writing the course's prerequisite fields
                writer.write("PREREQUISITES:");
                writer.write("\n");
                if (!c.getPrerequisites().isEmpty()) {
                    for (Course preCourse : c.getPrerequisites()) {
                        writer.write(preCourse.getCourseCode() + ";" + preCourse.getCourseName() + "\n");
                    }
                }
                writer.write("\n");
            }
        }
    }
    
    // Returns existing courses for application to use
    @Override
    public List<Course> getCourses() {
        return courses;
    }

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
