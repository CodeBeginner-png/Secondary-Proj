package CollegeProjects;
public class Student {
    private String name;
    private int id;
    private double gpa;

    public Student(String name, int id, double gpa) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive integer");
        }
        if (gpa < 0.0 || gpa > 4.0) {
            throw new IllegalArgumentException("GPA must be between 0.0 and 4.0");
        }

        // Initialize fields
        this.name = name;
        this.id = id;
        this.gpa = gpa;

    }

    // toString method
    @Override
    public String toString() {
        return "Student{name='" + name + "', id=" + id + ", gpa=" + gpa + "}";
    }

    // equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check for reference equality
        if (obj == null || getClass() != obj.getClass()) return false;

        Student student = (Student) obj;
        return id == student.id; // Students are equal if their IDs are equal
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Integer.hashCode(id); // Hash code based on ID
    }

}

        // Additional initialization logic can go here if needed