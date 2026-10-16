package Interfaces;
import Entity.Appointment;

public interface IHospitalOperations {
    void addAppointment(Appointment app);
    boolean cancelAppointment(String appointmentId);
    Appointment searchAppointment(String appointmentId);
    String getHospitalSummary();
}