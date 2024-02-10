package org.example;

public class Mission {
    Person[] people;

    public Mission(Person[] people) {
        this.people = people;
    } //Mission

    public String getPeople() {
        String output = "";
        for (Person elem : people) {
            output += "\n[";
            output += elem.getFirstName() + ", " + elem.getLastName() + ", " + elem.getDescription();
            output += "]";
        } //for
        return output;
    } //getPeople;

}
