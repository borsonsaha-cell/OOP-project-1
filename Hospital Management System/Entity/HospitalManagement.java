package Entity;
import Interfaces.IHospitalOperations;

public class HospitalManagement implements IHospitalOperations {

    private Patient patient;
    private Appointment[] appointments = new Appointment[20];

    public void setPatient(Patient p) {
        this.patient = p;
    }

    @Override
    public void addAppointment(Appointment app) {
        for (int i = 0; i < appointments.length; i++) {
            if (appointments[i] == null) {
                appointments[i] = app;
                break;
            }
        }
    }

    @Override
    public boolean cancelAppointment(String id) {
        for (int i = 0; i < appointments.length; i++) {
            if (appointments[i] != null &&
                appointments[i].getAppId().equalsIgnoreCase(id)) {
                appointments[i] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public Appointment searchAppointment(String id) {
        for (int i = 0; i < appointments.length; i++) {
            if (appointments[i] != null &&
                appointments[i].getAppId().equalsIgnoreCase(id)) {
                return appointments[i];
            }
        }
        return null;
    }

    @Override
    public String getHospitalSummary() {
        String data = "********  HOSPITAL RECORDS ********\n";

        if (patient != null) {
            data += patient.showDetails();
        }

        data += "---------------------------------------\n";

        for (int i = 0; i < appointments.length; i++) {
            if (appointments[i] != null) {
                data += "App ID: " + appointments[i].getAppId()
                     + " | Date: " + appointments[i].getDate() + "\n";
                data += "Doctor: "
                     + appointments[i].getDoctor().getName()
                     + " (" + appointments[i].getDoctor().getSpecialty() + ")\n\n";
            }
        }
        return data;
    }
}
