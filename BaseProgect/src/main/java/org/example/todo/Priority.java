package org.example.todo;

public enum Priority {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High"),
    CRITICAL("Critical");

    private final String label;

    // Constructor
    Priority(String label) {
        this.label = label;
    }

    // Getter method for the label
    public String getLabel() {
        return label;
    }
}
