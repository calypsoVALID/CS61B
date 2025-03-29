public class CS61BStudent { // Class Declaration
    public int idNumber; // Instance Variables
    public int grade;
    public static String instructor = "Hug"; // Class (Static) Variables
    public CS61BStudent(int id) { // Constructor
        this.idNumber = id;
        this.grade = 100;
    }
    public boolean watchLecture() { // Instance Method
        // Returns whether the student actually watched the lecture
        ...
    }
    public static String getInstructor() { // Static Method
        ...
    }
}

public class CS61B {
    public static String university = "Uc Berkeley";
    public String semester;
    public CS61BStudent[] students;

    public CS61B(int capacity, CS61BStudent[] signups, String semester) {
        this.semester = semester;
        students = new CS61BStudent[capacity];
        for (int i = 0; i < capacity; i++) {
            students[i] = signups[i];
        }
    }

    public int makeStudentsWatchLecture() {
        int total = 0;
        for (CS61BStudent student: students) {
            boolean watched = student.watchLecture();
            if (watched) {
                total += 1;
            }
        }
        return tatal;
    }

    public static void changeUniversity(String newUniversity) {
        CS61B.university = newUniversity;
    }
}