package Entity;

public class Appointment {
    private String appointmentId, date;
    private Doctor doctor;

    public Appointment(String id, String date, Doctor d) {
        this.appointmentId = id; this.date = date; this.doctor = d;
    }
    public String getAppId() { return appointmentId; }
    public String getDate() { return date; }
    public Doctor getDoctor() { return doctor; }
}