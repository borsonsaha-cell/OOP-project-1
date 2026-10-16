package Entity;

public class Doctor extends Person {
    private String specialty;
    public Doctor(String id, String name, String specialty) {
        super(id, name);
        this.specialty = specialty;
    }
    public String getSpecialty() { return specialty; }
    @Override
    public String showDetails() {
        return "Doctor: " + getName() + " [" + specialty + "]";
    }
}