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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import java.beans.*;

public class TodoApp extends Application {

    @Override
    public void start(Stage stage) {

        // Text field for task title
        TextField taskTitle = new TextField();
        taskTitle.setPromptText("Enter a title...");
        taskTitle.setFocusTraversable(false);

        // Text area for task description (much more place)
        TextArea taskDescription = new TextArea();
        taskDescription.setPromptText("Enter a description...");
        taskDescription.setFocusTraversable(false);
        taskDescription.setPrefRowCount(8);
        taskDescription.setWrapText(true);
        taskDescription.setPrefWidth(450);
        taskDescription.setMaxWidth(600);
        taskDescription.setMaxHeight(100);


        // Date picker for due date (calendar to pick a precise place)
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Select a date...");
        datePicker.setFocusTraversable(false);

        // ComboBox for priority selection (rolling list to pick between : high, normal & low)
        ComboBox<String> priorityBox = new ComboBox<>();
        priorityBox.getItems().addAll("High", "Normal", "Low");
        priorityBox.setPromptText("Select a priority...");
        priorityBox.setValue("Normal");
        priorityBox.setFocusTraversable(false);

        // TableView to display tasks (make a table, not a list)
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        TableView<Task> tableView = new TableView<>(tasks);
        tableView.setPlaceholder(new Label("No tasks yet!"));

        // Create table columns (columns with header already included)
        TableColumn<Task, Boolean> completedCol = new TableColumn<>("Completed");
        completedCol.setCellValueFactory(new PropertyValueFactory<>("completed"));
        completedCol.setPrefWidth(80);

        TableColumn<Task, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleCol.setPrefWidth(200);

        TableColumn<Task, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        descCol.setPrefWidth(600);

        TableColumn<Task, String> dateCol = new TableColumn<>("Due Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        dateCol.setPrefWidth(100);

        TableColumn<Task, String> priorityCol = new TableColumn<>("Priority");
        priorityCol.setCellValueFactory(new PropertyValueFactory<>("priority"));
        priorityCol.setPrefWidth(90);

        TableColumn<Task, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setPrefWidth(90);

        // Add columns to table
        tableView.getColumns().addAll(completedCol, titleCol, descCol, dateCol, priorityCol, statusCol);

        // Add task button
        Button addTask = new Button("Add Task");
        addTask.setOnAction(e -> {
            // Check if required fields are not empty
            if (!taskTitle.getText().isEmpty() && !taskDescription.getText().isEmpty() && datePicker.getValue() != null) {
                // Create new task and add to list
                tasks.add(new Task(
                    taskTitle.getText(),
                    taskDescription.getText(),
                    datePicker.getValue().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    priorityBox.getValue()
                ));
                // Clear input fields
                taskTitle.clear();
                taskDescription.clear();
                datePicker.setValue(null);
                priorityBox.setValue("Normal");
                System.out.println("Task added!");
            }
        });

        //JSON Object



        //Save button
        Button saveTask = new Button("Save Task");
        saveTask.setOnAction(e ->{

        });

        // Delete task button
        Button deleteTask = new Button("Delete Task");
        deleteTask.setOnAction(e -> {
            // Get selected task from table
            Task selectedTask = tableView.getSelectionModel().getSelectedItem();
            if (selectedTask != null) {
                // Remove selected task from list
                tasks.remove(selectedTask);
                System.out.println("Task deleted: " + selectedTask.getTitle());
            }
        });

        // Top bar: contains input fields and buttons
        HBox topBar = new HBox(10);
        topBar.setPadding(new Insets(20));
        topBar.getChildren().addAll(taskTitle, taskDescription, datePicker, priorityBox, addTask, deleteTask, saveTask);

        // Label for task list section
        Label visualizeTask = new Label("Visualize your tasks");
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
        visualizeTask.setFont(font);

        // Center area: contains label and table
        VBox centerArea = new VBox(10);
        centerArea.setPadding(new Insets(20));
        centerArea.getChildren().addAll(visualizeTask, tableView);

        // Main container: stacks topBar and centerArea vertically
        VBox root = new VBox();
        root.getChildren().addAll(topBar, centerArea);
        root.setStyle("-fx-background-color: BEIGE");

        // Create scene and configure stage
        Scene scene = new Scene(root, 1200, 800);
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