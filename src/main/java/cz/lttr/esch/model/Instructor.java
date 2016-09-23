package cz.lttr.esch.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Lukas Trumm on 08.08.2016
 */
public class Instructor {

    private final StringProperty nickName;
    private final StringProperty firstName;
    private final StringProperty lastName;

    public Instructor(String nickName, String firstName, String lastName) {
        this.nickName = new SimpleStringProperty(nickName);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
    }

    public Instructor(String nickName) {
        this(nickName, "", "");
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getNickName() {
        return nickName.get();
    }

    public StringProperty nickNameProperty() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName.set(nickName);
    }
}
