package org.example.todo;

import java.util.List;

public interface TodoListService {
    List<TodoList> getTodolist();
    TodoList saveTodo(TodoList todo);
    TodoList updateTodo(TodoList updatedTodo);
    TodoList deleteTodo(TodoList updatedTodo);
}
