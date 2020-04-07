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
    public CourseFileDao(String courseFile) throws Exception {
        this.courses = new ArrayList<>();
        this.courseFile = courseFile;
        try {
            boolean prerequisitesLine = false;
            Scanner scanner = new Scanner(new File(courseFile));
            Course latest = new Course("Placeholder", "Placeholder");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                // Handling the course's prerequisites part
                if (line.equals("PREREQUISITES:")) {
                    prerequisitesLine = true;
                }
                if (prerequisitesLine && scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    while (!line.equals("")) {
                        String[] preInfo = line.split(";");
                        String preCode = preInfo[0];
                        String preName = preInfo[1];
                        latest.addPrerequisite(new Course(preCode, preName));
                        if (scanner.hasNext()) {
                            line = scanner.nextLine();   
                        } else {
                            break;
                        }
                    }
                    prerequisitesLine = false;
                    continue;
                }
                
                // Creating existing courses
                String[] info = line.split(";");
                String code = info[0];
                String name = info[1];
                Course course = new Course(code, name);
                latest = course;
                courses.add(course);                
            }
        } catch (FileNotFoundException e) {
            FileWriter writer = new FileWriter(new File(courseFile));
            writer.close();
        }
    }
    
    // Writing new courses to file
    // CURRENTLY UNUSED AS NO CHANGES TO FILE ARE MADE DURING A SESSION!
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
}
