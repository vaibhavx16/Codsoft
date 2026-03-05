import java.util.*;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private int enrolled;
    private String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0;
        this.schedule = schedule;
    }

    public String getCode() {
        return code;
    }

    public boolean hasAvailableSlot() {
        return enrolled < capacity;
    }

    public void registerStudent() {
        if (hasAvailableSlot()) {
            enrolled++;
        }
    }

    public void dropStudent() {
        if (enrolled > 0) {
            enrolled--;
        }
    }

    public String getDetails() {
        return String.format("%s - %s | %s | Schedule: %s | Slots: %d/%d",
                code, title, description, schedule, (capacity - enrolled), capacity);
    }
}

class Student {
    private String id;
    private String name;
    private Map<String, Course> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void registerForCourse(Course course) {
        if (registeredCourses.containsKey(course.getCode())) {
            System.out.println("Already registered for this course.");
        } else if (course.hasAvailableSlot()) {
            registeredCourses.put(course.getCode(), course);
            course.registerStudent();
            System.out.println("Successfully registered for " + course.getCode());
        } else {
            System.out.println("Course is full. Cannot register.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.containsKey(course.getCode())) {
            registeredCourses.remove(course.getCode());
            course.dropStudent();
            System.out.println("Successfully dropped " + course.getCode());
        } else {
            System.out.println("You are not registered for this course.");
        }
    }

    public void displayRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("No registered courses.");
        } else {
            System.out.println("Registered Courses:");
            registeredCourses.values().forEach(course -> System.out.println(course.getDetails()));
        }
    }
}

public class StudentCourseRegistration {
    private static Map<String, Course> courseCatalog = new HashMap<>();
    private static Map<String, Student> studentDatabase = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        mainMenu();
    }

    private static void mainMenu() {
        while (true) {
            System.out.println("\n--- Student Course Registration ---");
            System.out.println("1. Add a New Course");
            System.out.println("2. Add a New Student");
            System.out.println("3. Display Available Courses");
            System.out.println("4. Register for a Course");
            System.out.println("5. Drop a Course");
            System.out.println("6. View Registered Courses");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1 -> addCourse();
                case 2 -> addStudent();
                case 3 -> displayCourses();
                case 4 -> registerForCourse();
                case 5 -> dropCourse();
                case 6 -> viewRegisteredCourses();
                case 7 -> {
                    System.out.println("Exiting. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addCourse() {
        System.out.print("Enter Course Code: ");
        String code = scanner.nextLine();
        System.out.print("Enter Course Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Course Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Course Capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Course Schedule: ");
        String schedule = scanner.nextLine();

        courseCatalog.put(code, new Course(code, title, description, capacity, schedule));
        System.out.println("Course added successfully.");
    }

    private static void addStudent() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        studentDatabase.put(id, new Student(id, name));
        System.out.println("Student added successfully.");
    }

    private static void displayCourses() {
        System.out.println("\nAvailable Courses:");
        courseCatalog.values().forEach(course -> System.out.println(course.getDetails()));
    }

    private static void registerForCourse() {
        Student student = getStudentById();
        if (student == null) return;

        displayCourses();
        System.out.print("Enter course code to register: ");
        String code = scanner.nextLine();

        Course course = courseCatalog.get(code);
        if (course != null) {
            student.registerForCourse(course);
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void dropCourse() {
        Student student = getStudentById();
        if (student == null) return;

        student.displayRegisteredCourses();
        System.out.print("Enter course code to drop: ");
        String code = scanner.nextLine();

        Course course = courseCatalog.get(code);
        if (course != null) {
            student.dropCourse(course);
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void viewRegisteredCourses() {
        Student student = getStudentById();
        if (student != null) {
            student.displayRegisteredCourses();
        }
    }

    private static Student getStudentById() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();

        Student student = studentDatabase.get(id);
        if (student == null) {
            System.out.println("Student not found.");
        }
        return student;
    }
}
