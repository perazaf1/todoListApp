import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Task class representing a to-do item.
 * Uses JavaFX Properties for automatic UI binding and updates.
 */
public class Task {

    // Properties: special JavaFX objects that automatically notify the UI when values change
    // This allows the TableView to update automatically when a task is modified
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty dueDate = new SimpleStringProperty();
    private final BooleanProperty completed = new SimpleBooleanProperty(false);
    private final StringProperty status = new SimpleStringProperty("Pending");
    private final StringProperty priority = new SimpleStringProperty("Normal");

    /**
     * Constructor: creates a new task with the given information
     * @param title - the task title
     * @param description - detailed description of the task
     * @param dueDate - when the task is due
     * @param priority - importance level (High, Normal, Low)
     */
    public Task(String title, String description, String dueDate, String priority) {
        this.title.set(title);
        this.description.set(description);
        this.dueDate.set(dueDate);
        this.priority.set(priority);
    }

    // ========== GETTERS ==========
    // These methods retrieve the current values

    public String getTitle() {
        return title.get();
    }
    public String getDescription() {
        return description.get();
    }
    public String getDueDate() {
        return dueDate.get();
    }
    public boolean isCompleted() {
        return completed.get();
    }
    public String getStatus() {
        return status.get();
    }
    public String getPriority() {
        return priority.get();
    }

    // ========== PROPERTY GETTERS ==========
    // These are REQUIRED for JavaFX TableView to work properly
    // The TableView uses these to bind columns to task data

    public StringProperty titleProperty() {
        return title;
    }
    public StringProperty descriptionProperty() {
        return description;
    }
    public StringProperty dueDateProperty() {
        return dueDate;
    }
    public BooleanProperty completedProperty() {
        return completed;
    }
    public StringProperty statusProperty() {
        return status;
    }
    public StringProperty priorityProperty() {
        return priority;
    }

    // ========== SETTERS ==========
    // These methods modify the task values

    public void setTitle(String title) {
        this.title.set(title);
    }
    public void setDescription(String description) {
        this.description.set(description);
    }
    public void setDueDate(String dueDate) {
        this.dueDate.set(dueDate);
    }

    /**
     * Mark task as completed or not completed
     * Automatically updates the status accordingly
     */
    public void setCompleted(boolean completed) {
        this.completed.set(completed);
        // If completed, status becomes "Done", otherwise "Pending"
        this.status.set(completed ? "Done" : "Pending");
    }

    /**
     * Toggle between completed and not completed
     * Useful for checkbox interactions
     */
    public void toggleCompleted() {
        boolean newValue = !completed.get(); // Flip the current value
        completed.set(newValue);
        status.set(newValue ? "Done" : "Pending");
    }

    /**
     * Set the task status
     * Also updates the completed flag if status is "Done"
     */
    public void setStatus(String status) {
        this.status.set(status);
        // If status is "Done", mark as completed
        completed.set(status.equals("Done"));
    }

    public void setPriority(String priority) {
        this.priority.set(priority);
    }

    /**
     * Returns a text representation of the task
     * Used when displaying the task as a string
     */
    @Override
    public String toString() {
        return (completed.get() ? "[✓] " : "[ ] ")
                + title.get()
                + " - " + description.get()
                + " (Due: " + dueDate.get()
                + ", Priority: " + priority.get()
                + ", Status: " + status.get() + ")";
    }
}