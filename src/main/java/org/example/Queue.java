package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Queue{

    private JFrame frame;
    private JPanel panel;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel subPanel2;
    private JPanel subSubPanel2;
    private JPanel panel3;
    private JTextField firstName1;
    private JTextField lastName1;
    private JTextField description;
    private JButton addPerson1;
    private JButton addMission;
    private JTextArea miniConsole;
    private JTextField firstName2;
    private JTextField lastName2;
    private JButton addPerson2;
    private JButton removePerson;
    private JTextArea tempConsole;
    public String tempConsoleText;
    public String miniConsoleText;
    private ArrayList<Person> peopleArray = new ArrayList<>();
    private ArrayList<Mission> missionArray = new ArrayList<>();

    public Queue() {
        frame = new JFrame();
        panel = new JPanel(new GridLayout(1,3));
        panel1 = new JPanel(new GridLayout(4, 1));
        panel2 = new JPanel(new GridLayout(4, 1));
        subPanel2 = new JPanel(new GridLayout(1, 2));
        subSubPanel2 = new JPanel(new GridLayout(2, 1));
        panel3 = new JPanel(new GridLayout(1, 1));
        firstName1 = new JTextField("Add First Name");
        lastName1 = new JTextField("Add Last Name");
        description = new JTextField("Add Description");
        addPerson1 = new JButton("Add Person to Database");
        addMission = new JButton("Add Mission to Queue");
        //AddScrollPane?
        miniConsoleText = new String("Version 0.0.1\nInitializing Mission Altercation Console\n");
        miniConsole = new JTextArea(miniConsoleText);
        firstName2 = new JTextField("Add First Name");
        lastName2 = new JTextField("Add Last Name");
        addPerson2 = new JButton("Add Person to Mission");
        removePerson = new JButton("Remove Person From Mission");
        tempConsoleText = new String("Version 0.0.1\nInitializing Active Mission Console\n");
        tempConsole = new JTextArea(tempConsoleText);
        setUp();
    } //Queue

    public void setUp() {
        frame.setSize(1000,600);
        frame.setName("Mission Queue - UGAHacks9");
        frame.add(panel);
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel1.add(firstName1);
        panel1.add(lastName1);
        panel1.add(description);
        panel1.add(addPerson1);
        panel2.add(miniConsole);
        panel2.add(firstName2);
        panel2.add(lastName2);
        panel2.add(subPanel2);
        subPanel2.add(addPerson2);
        subPanel2.add(subSubPanel2);
        subPanel2.add(addMission);
        subSubPanel2.add(addPerson2);
        subSubPanel2.add(removePerson);
        panel3.add(tempConsole);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        setUpListeners();
    } //setUp

    public void setUpListeners() {

        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object o = e.getSource();
                if (o == addPerson1) {
                    try {
                        if ((firstName1.getText().isEmpty() || lastName1.getText().isEmpty() ||
                                firstName1.getText().equals("Add First Name") || lastName1.getText().equals("Add Last Name"))) {
                            throw new IllegalArgumentException();
                        }
                        addPerson(firstName1.getText(), lastName1.getText());
                        updateMiniConsoleText("Added: " + firstName1.getText() + " " + lastName1.getText());
                    } catch (IllegalArgumentException exception) {
                        updateMiniConsoleText("Error: Incorrect Person Information Added");
                    }
                } else if (o == addPerson2) {
                    try {
                        if ((firstName2.getText().isEmpty() || lastName2.getText().isEmpty() ||
                                firstName2.getText().equals("Add First Name") || lastName2.getText().equals("Remove Last Name"))) {
                            throw new IllegalArgumentException();
                        } //if
                    } catch (IllegalArgumentException exception) {
                        updateMiniConsoleText("Person does not exist");
                    } //try
                } else if (o == addMission) {
                    updateTempConsoleText("test3");
                } else if (o == removePerson) {
                    updateMiniConsoleText("removed person");
                }
            }
        };

        addPerson1.addActionListener(buttonListener);
        addPerson2.addActionListener(buttonListener);
        addMission.addActionListener(buttonListener);
        removePerson.addActionListener(buttonListener);

    } //setUpListeners


    public void updateMiniConsoleText(String text) {
        String oldText = miniConsole.getText();
        miniConsole.setText(text + "\n" + oldText);
    } //updateMiniConsoleText

    public void updateTempConsoleText(String text) {
        String oldText = tempConsole.getText();
        tempConsole.setText(text + "\n" + oldText);
    } //updateTempConsoleText

    public void addPerson(String first, String last) {
        if (!description.getText().isEmpty()) {
            Person person = new Person(first, last);
            person.addDescription(description.getText());
            peopleArray.add(person);
        } else {
            peopleArray.add(new Person(first, last));
        } //if
    } //addPeople



    /**
     * Singular Panel
     * Allows for the addition of "people" with specific modifiers
     * It is a priority queue / "Mission Queue"
     * Allows for the reading of csvs, or API database
     */

    public static void main(String[] args) {
        new Queue();
    }

}
