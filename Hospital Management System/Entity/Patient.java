package Entity;

public class Patient extends Person {
    private int age;
    public Patient(String id, String name, int age) { 
        super(id, name); 
        this.age = age;
    }
    @Override
    public String showDetails() {
        return "Patient Name: " + getName() + " | ID: " + getId() + " | Age: " + age + "\n";
    }
}