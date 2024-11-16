package org.example.todo;

import org.zkoss.zk.ui.util.Clients;

import java.util.*;


public class TodoListServiceImpl implements TodoListService{
    List<TodoList> todos = new ArrayList<>();
    @Override
    public List<TodoList> getTodolist() {
        // Simulate data retrieval with some sample todo items

        todos.add(new TodoList("Buy Groceries", "Milk, Eggs, Bread", Priority.HIGH,true,new Date()));
        todos.add(new TodoList("Workout", "Morning run and gym", Priority.MEDIUM,false,new Date()));
        todos.add(new TodoList("Project Meeting", "Discuss project progress", Priority.CRITICAL,true,new Date()));
        return todos;
    }
    @Override
    public TodoList saveTodo(TodoList todo){
        if (todo != null){
            todos.add(todo);
            Clients.showNotification("task added");
        }else {
            Clients.showNotification("Nothing added");
        }
        return todo;
    }
    @Override
    public TodoList updateTodo(TodoList updatedTodo) {
        for (int i = 0; i < todos.size(); i++) {
            TodoList existingTodo = todos.get(i);
            // Assume the title is unique and use it for matching
            if (existingTodo.getTitle().equals(updatedTodo.getTitle())) {
                todos.set(i, updatedTodo);  // Replace the old todo with the new one
                Clients.showNotification("Task updated");
                return updatedTodo;
            }
        }
        Clients.showNotification("Task not found");
        return updatedTodo;
    }
    public TodoList deleteTodo(TodoList deleteTodo){
        for (int i = 0; i < todos.size(); i++) {
            TodoList existingTodo = todos.get(i);
            if (existingTodo.getTitle().equals(deleteTodo.getTitle())){
                todos.remove(deleteTodo);
                Clients.showNotification("deleted successful!");
                return deleteTodo;

            }
        }
            Clients.showNotification("Task not found");
            return deleteTodo;

    }

    }
