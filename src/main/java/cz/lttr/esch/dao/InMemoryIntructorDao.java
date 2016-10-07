package cz.lttr.esch.dao;

import cz.lttr.esch.model.Instructor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Lukas Trumm on 07.10.2016
 */
public class InMemoryIntructorDao {

    private ObservableList<Instructor> instructors = FXCollections.observableArrayList();

    public InMemoryIntructorDao() {
        fillInstructors();
    }

    public ObservableList<Instructor> getInstructors() {
        return instructors;
    }

    private void fillInstructors() {
        instructors.add(new Instructor("Koumák", "Petr", "Novák"));
        instructors.add(new Instructor("Vařečka", "Alžběta", "Pečená"));
        instructors.add(new Instructor("Koumák"));
    }

}
