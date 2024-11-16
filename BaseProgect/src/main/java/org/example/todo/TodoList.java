package org.example.todo;

import java.util.Date;

public class TodoList {
    private String title;
    private String description;
    private Priority priority;
    private boolean complete;
    private Date date;// New field
    public TodoList() {
    }
    // Constructor
    public TodoList(String title, String description, Priority priority, boolean complete, Date date) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.complete = complete;

        this.date = date;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    // Getter for complete
    public boolean isComplete() {
        return complete;
    }

    // Setter for complete
    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
