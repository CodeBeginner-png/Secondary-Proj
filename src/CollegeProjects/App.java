package CollegeProjects;

public class App {
    public static void main(String[] args) throws Exception{
        // Create Student objects
        Student Tom = new Student("Tom", 1, 4.0);
        Student Alan = new Student("Alan", 2, 4.0);
        Student Kyle = new Student("Kyle", 3, 4.0);
        
        System.out.println(Tom.toString());
        System.out.println(Alan.toString());
        System.out.println(Kyle.toString());
    }

        }