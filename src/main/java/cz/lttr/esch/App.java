package cz.lttr.esch;

import cz.lttr.esch.controller.FirstController;
import cz.lttr.esch.model.Instructor;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    // TEMPORARY
    private ObservableList<Instructor> instructors = FXCollections.observableArrayList();

    // TEMPORARY
    public ObservableList<Instructor> getInstructors() {
        return instructors;
    }

    // TEMPORARY
    private void fillInstructors() {
        instructors.add(new Instructor("Koumák", "Petr", "Novák"));
        instructors.add(new Instructor("Vařečka", "Alžběta", "Pečená"));
        instructors.add(new Instructor("Koumák"));
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Esch");
        initRootLayout();

        // TEMPORARY
        fillInstructors();

        // TEMPORARY
        showInstructors();
    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/RootLayout.fxml"));
            this.rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TEMPORARY
    private void showInstructors() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/InstructorList.fxml"));
            AnchorPane instructorList = loader.load();

            rootLayout.setCenter(instructorList);

            FirstController controller = loader.getController();
            controller.setApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
