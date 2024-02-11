package org.example;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Optional;

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
    private ArrayList<Mission> launchedMissions = new ArrayList<>();
    private Person[] personArray = new Person[10];
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;


    public Queue() {
        frame = new JFrame();
        panel = new JPanel(new GridLayout(1,3));
        panel1 = new JPanel(new GridLayout(4, 1));
        panel2 = new JPanel(new GridLayout(4, 1));
        subPanel2 = new JPanel(new GridLayout(1, 2));
        subSubPanel2 = new JPanel(new GridLayout(2, 1));
        panel3 = new JPanel(new GridLayout(1, 1));
        //firstName1 = new JTextField("Add First Name");
        firstName1 = new JTextField("");

        //lastName1 = new JTextField("Add Last Name");
        lastName1 = new JTextField("");

        //description = new JTextField("Add Description");
        description = new JTextField("");

        addPerson1 = new JButton("Add Person to Database");
        addMission = new JButton("Add Mission to Queue");
        miniConsoleText = new String("Version 0.0.1\nInitializing Mission Altercation Console\n");
        miniConsole = new JTextArea(miniConsoleText);
        //firstName2 = new JTextField("Add First Name");
        firstName2 = new JTextField("");

        //lastName2 = new JTextField("Add Last Name");
        lastName2 = new JTextField("");

        addPerson2 = new JButton("Add Person to Mission");
        removePerson = new JButton("Remove Person From Mission");
        tempConsoleText = new String("Version 0.0.1\nInitializing Active Mission Console\n");
        tempConsole = new JTextArea(tempConsoleText);

        scrollPane1 = new JScrollPane(miniConsole);
        scrollPane2 = new JScrollPane(tempConsole);

        Mission mission1 = new Mission(personArray);
        missionArray.add(mission1);

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
        panel2.add(scrollPane1);
        panel2.add(firstName2);
        panel2.add(lastName2);
        panel2.add(subPanel2);
        subPanel2.add(addPerson2);
        subPanel2.add(subSubPanel2);
        subPanel2.add(addMission);
        subSubPanel2.add(addPerson2);
        subSubPanel2.add(removePerson);
        panel3.add(scrollPane2);
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
                        addPersonToMission(firstName2.getText(), lastName2.getText());
                        updateMiniConsoleText("Mission " + launchedMissions.size() + 1 + " Added: " + firstName2.getText() + " " + lastName2.getText());
                    } catch (IllegalArgumentException exception) {
                        updateMiniConsoleText("Person does not exist");
                    } catch (IndexOutOfBoundsException exception) {
                        updateMiniConsoleText("Mission " + launchedMissions.size() + " is full.");
                    } //try
                } else if (o == addMission) {
                    try {
                        launchMission(missionArray.get(0));
                    } catch (IllegalArgumentException exception) {
                        updateMiniConsoleText("Mission not added.");
                    } catch (NullPointerException exception) {
                        updateMiniConsoleText("Mission information could not be output.");
                    } //try
                } else if (o == removePerson) {
                    try {
                        removePersonFromMission(firstName2.getText(), lastName2.getText());
                        System.out.println("Mission " + launchedMissions.size() + 1 + ":\n");
                        updateMiniConsoleText(missionArray.get(0).getPeople());
                    } catch (IllegalArgumentException exception) {
                        updateMiniConsoleText("Person not found.");
                    } catch (NullPointerException exception) {
                        updateMiniConsoleText("Mission information could not be output.");
                        System.out.println(personArray[0].getFirstName());
                    } //try
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
        miniConsole.setText(oldText + "\n" + text);
    } //updateMiniConsoleText

    public void updateTempConsoleText(String text) {
        String oldText = tempConsole.getText();
        tempConsole.setText(oldText + "\n" + text);
    } //updateTempConsoleText

    public void addPerson(String first, String last) {
        if (duplicatePerson(first, last)) {
            throw new IllegalArgumentException();
        } else {
            if (!description.getText().isEmpty()) {
                Person person = new Person(first, last);
                person.addDescription(description.getText());
                peopleArray.add(person);
            } else {
                peopleArray.add(new Person(first, last));
            } //if
        }
    } //addPeople

    public void addPersonToMission(String first, String last) {
        Person person = peopleArray.get(getIndexOfPerson(first, last));
        if (duplicateMissionPerson(first, last)) {
            throw new IllegalArgumentException();
        } else {
            for (int i = 0; i < 10; i++) {
                if (personArray[i] == null) {
                    personArray[i] = person;
                    i = 10;
                } //if
                if (i == 9) {
                    throw new IndexOutOfBoundsException();
                }
            } //for
        }
    }

    public int getIndexOfPerson(String firstName, String lastName) {
        boolean caught = false;
        int index = 0;
        if (peopleArray.isEmpty()) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < peopleArray.size(); i++) {
            if (firstName.equals(peopleArray.get(i).getFirstName()) && lastName.equals(peopleArray.get(i).getLastName())) {
                index = i;
                caught = true;
                break;
            }
        }
        if (!caught) {
            throw new IllegalArgumentException();
        }
        return index;
    } //getIndexOfPerson

    public boolean duplicateMissionPerson(String first, String last) {
        if (personArray.length == 0) {
            return false;
        } else {
            try {
                for (Person elem : personArray) {
                    if (elem.getFirstName().equals(first) && elem.getLastName().equals(last)) {
                        return true;
                    } //if
                } //for
            } catch (NullPointerException exception) {
                return false;
            }
        }
        return false;
    } //duplicateMissionPerson

    public boolean duplicatePerson(String first, String last) {
        if (peopleArray.isEmpty()) {
            return false;
        }
        for (Person elem : peopleArray) {
            if (elem.getFirstName().equals(first) && elem.getLastName().equals(last)) {
                return true;
            }
        }
        return false;
    } //duplicatePerson

    public void removePersonFromMission(String first, String last) {
        if (personArray.length == 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < personArray.length; i++) {
            if (personArray[i] == null) {
                i = 10;
                throw new IllegalArgumentException();
            } //if
            if (personArray[i].getFirstName().equals(first)&&personArray[i].getLastName().equals(last)) {
                Person[] tempArray = new Person[10];
                personArray = deleteFromArray(tempArray, i);
                i = 10;
            }
        }
    } //removePersonFromMission

    public Person[] deleteFromArray(Person[] array, int index) {
        if (index < 0 || index >= array.length) {
            return array;
        }
        Person[] temp = new Person[9];
        for (int i = 0; i < index; i++) {
            temp[i] = array[i];
        }
        for (int i = index; i < array.length - 1; i++) {
            temp[i] = array[i + 1];
        }
        Person[] finalTemp = new Person[10];
        for (int i = 0; i < 9; i++) {
            finalTemp[i] = temp[i];
        }
        return finalTemp;
    } //deleteFromArray

    public void launchMission(Mission mission) {
        launchedMissions.add(mission);
        StringBuilder output = Optional.ofNullable(miniConsoleText).map(StringBuilder::new).orElse(null);
        for (Mission elem : launchedMissions) {
            output = (output == null ? new StringBuilder("null") : output).append("\n").append(elem.getPeople());
        } //for
        miniConsole.setText(output.toString());
    }


    public static void main(String[] args) {
        new Queue();
    }

}
