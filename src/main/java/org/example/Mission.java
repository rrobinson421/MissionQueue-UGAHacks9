package org.example;

public class Mission {
    Person[] people;

    public Mission(Person[] people) {
        this.people = people;
    } //Mission

    public String getPeople() {
        try {
            String output = "";
            if (people[0] == null) {
                output = "[]";
            } else {
                for (Person elem : people) {
                    output += "\n[";
                    output += elem.getFirstName() + ", " + elem.getLastName() + ", " + elem.getDescription();
                    output += "]";
                } //for
            }
            return output;
        } catch (NullPointerException exception) {
            throw new NullPointerException();
        }
    } //getPeople;

}
