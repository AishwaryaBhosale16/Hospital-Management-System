import java.util.ArrayList;
import java.util.Scanner;

// Patient class to store details
class Patient {
    private int id;
    private String name;
    private int age;
    private String disease;

    public Patient(int id, String name, int age, String disease) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDisease() { return disease; }

    @Override
    public String toString() {
        return "Patient ID: " + id + ", Name: " + name + ", Age: " + age + ", Disease: " + disease;
    }
}

// Appointment class to store patient appointments
class Appointment {
    private int patientId;
    private String date;
    private String time;

    public Appointment(int patientId, String date, String time) {
        this.patientId = patientId;
        this.date = date;
        this.time = time;
    }

    public int getPatientId() { return patientId; }
    public String getDate() { return date; }
    public String getTime() { return time; }

    @Override
    public String toString() {
        return "Appointment -> Patient ID: " + patientId + ", Date: " + date + ", Time: " + time;
    }
}

// Hospital Management System
public class HospitalManagementSystem {
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static ArrayList<Appointment> appointments = new ArrayList<>();
    private static int nextId = 1;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n🏥 Hospital Management System");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. Delete Patient");
            System.out.println("4. Book Appointment");
            System.out.println("5. View Appointments");
            System.out.println("6. Cancel Appointment");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    addPatient(sc);
                    break;
                case 2:
                    viewPatients();
                    break;
                case 3:
                    deletePatient(sc);
                    break;
                case 4:
                    bookAppointment(sc);
                    break;
                case 5:
                    viewAppointments();
                    break;
                case 6:
                    cancelAppointment(sc);
                    break;
                case 7:
                    System.out.println("Exiting... Stay Healthy! 🏥");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // Add new patient
    private static void addPatient(Scanner sc) {
        System.out.print("Enter patient name: ");
        String name = sc.nextLine();
        System.out.print("Enter patient age: ");
        int age = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter disease: ");
        String disease = sc.nextLine();

        patients.add(new Patient(nextId++, name, age, disease));
        System.out.println("✅ Patient added successfully!");
    }

    // View all patients
    private static void viewPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }
        System.out.println("\n🔍 Patient Records:");
        for (Patient p : patients) {
            System.out.println(p);
        }
    }

    // Delete patient by ID
    private static void deletePatient(Scanner sc) {
        System.out.print("Enter Patient ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        boolean removed = patients.removeIf(p -> p.getId() == id);
        if (removed) {
            appointments.removeIf(a -> a.getPatientId() == id); // Remove appointments if patient is deleted
            System.out.println("✅ Patient deleted successfully!");
        } else {
            System.out.println("❌ Patient not found!");
        }
    }

    // Book an appointment
    private static void bookAppointment(Scanner sc) {
        System.out.print("Enter Patient ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        
        // Check if patient exists
        boolean exists = false;
        for (Patient p : patients) {
            if (p.getId() == id) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            System.out.println("❌ Patient not found! Please add the patient first.");
            return;
        }

        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
        String date = sc.nextLine();
        System.out.print("Enter Appointment Time (HH:MM AM/PM): ");
        String time = sc.nextLine();

        appointments.add(new Appointment(id, date, time));
        System.out.println("✅ Appointment booked successfully!");
    }

    // View all appointments
    private static void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }
        System.out.println("\n📅 Appointment Records:");
        for (Appointment a : appointments) {
            System.out.println(a);
        }
    }

    // Cancel an appointment
    private static void cancelAppointment(Scanner sc) {
        System.out.print("Enter Patient ID to cancel appointment: ");
        int id = sc.nextInt();
        sc.nextLine();

        boolean removed = appointments.removeIf(a -> a.getPatientId() == id);
        if (removed) {
            System.out.println("✅ Appointment canceled successfully!");
        } else {
            System.out.println("❌ No appointment found for this Patient ID!");
        }
    }
}
