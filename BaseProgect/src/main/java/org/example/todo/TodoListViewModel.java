package org.example.todo;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TodoListViewModel implements Serializable {
    //data for the view
    String title;
    ListModelList<TodoList> todoListModel;

    private TodoList selectedTodo;
    private final  TodoListService todoListService = new TodoListServiceImpl();
    public ListModelList<TodoList> getTodoListModel() {

        System.out.println(todoListModel);
        return todoListModel;
    }

    public void setTodoListModel(ListModelList<TodoList> todoListModel) {
        this.todoListModel = todoListModel;
    }

    public TodoList getSelectedTodo() {
        return selectedTodo;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setSelectedTodo(TodoList selectedTodo) {
        this.selectedTodo = selectedTodo;
    }

    public List<Priority> getPriorityList(){
        return Arrays.asList(Priority.values());
    }
//    @Init // Ensure this is annotated for ZK to recognize it as the initializer
//    public void init() {
//        List<TodoList> todoLists = todoListService.getTodolist();
//        todoListModel = new ListModelList<>(todoLists);
//
//        selectedTodo = todoLists.isEmpty() ? null : todoLists.get(0); // Ensure non-null initial value
//
//        // Notify ZK to update the binding in the UI
//        BindUtils.postNotifyChange(null, null, this, "todoListModel");
//        BindUtils.postNotifyChange(null, null, this, "selectedTodo");
//
//        todoListModel = new ListModelList<>(Arrays.asList(
//                new TodoList("Static Task 1", "Description", Priority.HIGH, false, new Date()),
//                new TodoList("Static Task 2", "Description", Priority.MEDIUM, true, new Date())
//        ));
//    }

    @Init
    public void init() {
        System.out.println("Init method called");  // Check if this outputs in the console

        // Fetch existing todos from the service or initialize with sample data
        List<TodoList> todoLists = todoListService.getTodolist();

        if (todoLists.isEmpty()) {
            // Initialize with sample data if no data is returned
            todoLists = Arrays.asList(
                    new TodoList("Static Task 1", "Description", Priority.HIGH, false, new Date()),
                    new TodoList("Static Task 2", "Description", Priority.MEDIUM, true, new Date())
            );
        }

        // Initialize todoListModel with data
        todoListModel = new ListModelList<>(todoLists);
        selectedTodo = todoLists.isEmpty() ? null : todoLists.get(0); // Set initial selected item if available

        // Notify ZK to update bindings in the UI
        BindUtils.postNotifyChange(null, null, this, "todoListModel");
        BindUtils.postNotifyChange(null, null, this, "selectedTodo");
    }
    @Command
    @NotifyChange("todoListModel")
    public void completeTodo(@BindingParam("todo") TodoList todo) {
        // Update the 'complete' status for the todo item
        todo.setComplete(!todo.isComplete());
        todoListService.updateTodo(todo);  // Ensure service method exists for updates
    }

    @Command
    @NotifyChange({"selectedTodo","title"})
    public void addTodo(){
        if (Strings.isBlank(title)){
            Clients.showNotification("Subject is blank, nothing to do ?");
        }else{
            selectedTodo = todoListService.saveTodo(new TodoList(title,"",Priority.MEDIUM,false,new Date()));
            todoListModel.add(selectedTodo);
            todoListModel.addToSelection(selectedTodo);
            title = null;
        }
    }
    @Command
    @NotifyChange("selectedTodo")
    public void updateTodo(){
        //update data
        selectedTodo = todoListService.updateTodo(selectedTodo);

        //update the model, by using ListModelList, you don't need to notify todoListModel change
        //by reseting an item , it make listbox only refresh one item
        todoListModel.set(todoListModel.indexOf(selectedTodo), selectedTodo);
    }

    //when user clicks the update button
    @Command @NotifyChange("selectedTodo")
    public void reloadTodo(){
        //do nothing, the selectedTodo will reload by notify change
    }

    @Command
    //@NotifyChange("selectedTodo") //use postNotifyChange() to notify dynamically
    public void deleteTodo(@BindingParam("todo") TodoList todo){
        //save data
        todoListService.deleteTodo(todo);

        //update the model, by using ListModelList, you don't need to notify todoListModel change
        todoListModel.remove(todo);

        if(todo.equals(selectedTodo)){
            //refresh selected todo view
            selectedTodo = null;
            //for the case that notification is decided dynamically
            BindUtils.postNotifyChange(null, null, this, "selectedTodo");
        }
    }

}
