package GUI;

import Entity.*;
import File.FileIO;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class HospitalGUI extends JFrame implements ActionListener {
    private JTextField pNameTF, pIdTF, pAgeTF, appIdTF, dateTF, dNameTF, dIdTF, specTF;
    private JTextArea displayArea;
    private JButton regPatientBtn, addAppBtn, searchBtn, updateBtn, cancelBtn, saveBtn, clearBtn;
    private HospitalManagement hospital = new HospitalManagement();

    public HospitalGUI() {
        setTitle("HOSPITAL MANAGEMENT SYSTEM");
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        this.setIconImage(new ImageIcon("aiub_logo.png").getImage());

        JLabel header = new JLabel("HOSPITAL APPOINTMENT SYSTEM", JLabel.CENTER);
        header.setBounds(0, 0, 1100, 70);
        header.setOpaque(true);
        header.setBackground(new Color(180, 40, 40));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 26));
        add(header);

        
        JPanel p1 = createPanel("PATIENT INFO", 30, 90, 300, 280);
        pNameTF = addField(p1, "Patient Name:", 30, 260, 20);
        pIdTF = addField(p1, "Patient ID:", 90, 260, 20);
        pAgeTF = addField(p1, "Patient Age:", 150, 260, 20);
        regPatientBtn = createBtn("REGISTER PATIENT", 220, 20, 260, new Color(41, 128, 185));
        p1.add(regPatientBtn); add(p1);

        
        JPanel p2 = createPanel("APPOINTMENT & DOCTOR DETAILS", 350, 90, 700, 300);
        appIdTF = addField(p2, "App ID:", 30, 220, 20);
        dateTF = addField(p2, "Date (DD/MM/YY):", 90, 220, 20);
        dNameTF = addField(p2, "Doctor Name:", 30, 220, 380);
        dIdTF = addField(p2, "Doctor ID:", 90, 220, 380);
        specTF = addField(p2, "Specialty:", 150, 220, 380);
        
        addAppBtn = createBtn("BOOK", 225, 20, 190, new Color(39, 174, 96));
        searchBtn = createBtn("SEARCH", 225, 230, 190, new Color(52, 152, 219));
        updateBtn = createBtn("UPDATE", 225, 440, 190, new Color(155, 89, 182));
        p2.add(addAppBtn); p2.add(searchBtn); p2.add(updateBtn); add(p2);

        cancelBtn = createBtn("CANCEL APPOINTMENT", 400, 80, 250, new Color(192, 57, 43));
        saveBtn = createBtn("SAVE RECORDS", 400, 420, 250, new Color(230, 126, 34));
        clearBtn = createBtn("CLEAR ALL", 400, 760, 250, Color.DARK_GRAY);
        add(cancelBtn); add(saveBtn); add(clearBtn);

        displayArea = new JTextArea();
        displayArea.setFont(new Font("Monospaced", Font.BOLD, 14));
        displayArea.setEditable(false);
        JScrollPane sp = new JScrollPane(displayArea);
        sp.setBounds(30, 470, 1020, 220);
        sp.setBorder(new TitledBorder("HOSPITAL DATA LOG"));
        add(sp);

        setVisible(true);
    }

    private JPanel createPanel(String t, int x, int y, int w, int h) {
        JPanel p = new JPanel(null); p.setBounds(x, y, w, h); p.setBackground(Color.WHITE);
        p.setBorder(new TitledBorder(new LineBorder(Color.GRAY), t)); return p;
    }
    private JTextField addField(JPanel p, String l, int y, int w, int x) {
        JLabel lbl = new JLabel(l); lbl.setBounds(x, y, w, 20); p.add(lbl);
        JTextField tf = new JTextField(); tf.setBounds(x, y + 22, w, 30); p.add(tf); return tf;
    }
    private JButton createBtn(String t, int y, int x, int w, Color c) {
        JButton b = new JButton(t); b.setBounds(x, y, w, 40); b.setBackground(c);
        b.setForeground(Color.WHITE); b.setFont(new Font("Arial", Font.BOLD, 12));
        b.addActionListener(this); return b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == regPatientBtn) {
                if(pNameTF.getText().isEmpty()) throw new Exception("Patient Name Empty!");
                hospital.setPatient(new Patient(pIdTF.getText(), pNameTF.getText(), Integer.parseInt(pAgeTF.getText())));
                JOptionPane.showMessageDialog(this, "Patient Registered.");
            } else if (e.getSource() == addAppBtn) {
                Doctor d = new Doctor(dIdTF.getText(), dNameTF.getText(), specTF.getText());
                hospital.addAppointment(new Appointment(appIdTF.getText(), dateTF.getText(), d));
            } else if (e.getSource() == searchBtn) {
                Appointment a = hospital.searchAppointment(appIdTF.getText());
                if(a != null) {
                    dateTF.setText(a.getDate());
                    dNameTF.setText(a.getDoctor().getName());
                    dIdTF.setText(a.getDoctor().getId());
                    specTF.setText(a.getDoctor().getSpecialty());
                } else throw new Exception("Appointment not found!");
            } else if (e.getSource() == updateBtn) {
                if(hospital.cancelAppointment(appIdTF.getText())) {
                    Doctor d = new Doctor(dIdTF.getText(), dNameTF.getText(), specTF.getText());
                    hospital.addAppointment(new Appointment(appIdTF.getText(), dateTF.getText(), d));
                    JOptionPane.showMessageDialog(this, "Appointment Updated!");
                }
            } else if (e.getSource() == cancelBtn) {
                if(hospital.cancelAppointment(appIdTF.getText()))
                    JOptionPane.showMessageDialog(this, "Appointment Cancelled.");
            } else if (e.getSource() == saveBtn) {
                FileIO.saveInFile(displayArea.getText());
                JOptionPane.showMessageDialog(this, "Saved to HospitalRecords/appointments.txt");
            } else if (e.getSource() == clearBtn) {
                pNameTF.setText(""); pIdTF.setText(""); pAgeTF.setText("");
                appIdTF.setText(""); dateTF.setText(""); dNameTF.setText("");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
        displayArea.setText(hospital.getHospitalSummary());
    }
}