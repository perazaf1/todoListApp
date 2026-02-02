import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


public class TodoApp extends Application {

    @Override
    public void start(Stage stage) {

        // Text field for task input
        TextField taskDescription = new TextField();
        taskDescription.setPromptText("Enter a new task...");
        taskDescription.setFocusTraversable(false);

        // ListView to display tasks
        ObservableList<String> tasks = FXCollections.observableArrayList();
        ListView<String> listView = new ListView<>(tasks);

        // Buttons
        // Add task button
        Button addTask = new Button("Add a task");
        addTask.setOnAction(e -> {
        if (!taskDescription.getText().isEmpty()) {
        tasks.add(taskDescription.getText()); // Add the text from the text field to the list
        taskDescription.clear(); // Clear the text field after adding
        System.out.println("Task added!");
            }
        });

        // Delete task button
        Button deleteTask = new Button("Delete a task");
        deleteTask.setOnAction(e -> {
            String selectedTask = listView.getSelectionModel().getSelectedItem(); //get the selected item
            if (selectedTask != null) {
                tasks.remove(selectedTask); //remove the selected item from the list
                System.out.println("Task deleted: " + selectedTask);
            }
        });

        // Top bar: HBox containing text field and buttons
        HBox topBar = new HBox(10);
        topBar.setPadding(new Insets(20));
        topBar.getChildren().addAll(taskDescription, addTask, deleteTask);

        // Label for the task list
        Label visualizeTask = new Label("Visualize your tasks");
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
        visualizeTask.setFont(font);


        // Center area: VBox containing label and ListView
        VBox centerArea = new VBox(10);
        centerArea.setPadding(new Insets(20));
        centerArea.getChildren().addAll(visualizeTask, listView);

        // Main container: VBox that stacks topBar and centerArea
        VBox root = new VBox();
        root.getChildren().addAll(topBar, centerArea);
        root.setStyle("-fx-background-color: BEIGE");

        // Scene
        Scene scene = new Scene(root, 800, 600);
        Image icon = new Image(getClass().getResourceAsStream("/icon.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Todo List App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}