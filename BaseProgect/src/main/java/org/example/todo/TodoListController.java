package org.example.todo;

import com.google.javascript.jscomp.WarningsGuard;
import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.*;

import java.util.Date;
import java.util.List;

public class TodoListController extends SelectorComposer<Component> {
    @Wire
    Listbox todoListbox;
    @Wire
    Radiogroup selectedTodoPriority;
    @Wire
    Textbox todoSubject;


    @Wire
    Button addTodo;

    @Wire
    Component selectedTodoBlock;
    @Wire
    Checkbox selectedTodoCheck;
    @Wire
    Textbox selectedTodoSubject;
    @Wire
    Datebox selectedTodoDate;
    @Wire
    Textbox selectedTodoDescription;
    @Wire
    Button updateSelectedTodo;


    TodoListService todoListService = new TodoListServiceImpl();
    //data for the view
    ListModelList<TodoList> todoListModel;
    ListModelList<Priority> priorityListModel;
    TodoList selectedTodo;

    @Override
    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);

        //get data from service and wrap it to list-model for the view
        List<TodoList> todoLists = todoListService.getTodolist();
        todoListModel = new ListModelList<TodoList>(todoLists);
        todoListbox.setModel(todoListModel);

        priorityListModel = new ListModelList<Priority>(Priority.values());
        selectedTodoPriority.setModel(priorityListModel);
    }
    @Listen("onClick = #addTodo; onOK = #todoSubject")
    public void doTodoAdd(){

        String subject = todoSubject.getValue();
        if(Strings.isBlank(subject)){
            Clients.showNotification("Nothing to do ?",todoSubject);
        }else {
            selectedTodo = todoListService.saveTodo(new TodoList(subject,"",Priority.MEDIUM,false,new Date()));
            todoListModel.add(selectedTodo);

            refreshDetailView();
            todoSubject.setValue("");
        }
    }

    @Listen("onSelect = #todoListbox")
    public void doTodoSelect() {
        if(todoListModel.isSelectionEmpty()){
            //just in case for the no selection
            selectedTodo = null;
        }else{
            selectedTodo = todoListModel.getSelection().iterator().next();
        }
        refreshDetailView();
    }


    public void refreshDetailView(){
        if(selectedTodo==null){
            //clean
            selectedTodoBlock.setVisible(false);
            selectedTodoCheck.setChecked(false);
            selectedTodoSubject.setValue(null);
            selectedTodoDate.setValue(null);
            selectedTodoDescription.setValue(null);

            priorityListModel.clearSelection();
        }else{
            selectedTodoBlock.setVisible(true);
            selectedTodoCheck.setChecked(selectedTodo.isComplete());
            selectedTodoSubject.setValue(selectedTodo.getTitle());
            selectedTodoDate.setValue(selectedTodo.getDate());
            selectedTodoDescription.setValue(selectedTodo.getDescription());
//            updateSelectedTodo.setDisabled(false);

            priorityListModel.addToSelection(selectedTodo.getPriority());
        }
    }
    @Listen("onClick = #update")
    public void  doUpdateClick(){
        if(Strings.isBlank(selectedTodoSubject.getValue())){
            Clients.showNotification("nothing selected",selectedTodoSubject);
            return;
        }else {
            selectedTodo.setComplete(selectedTodoCheck.isChecked());
            selectedTodo.setTitle(selectedTodoSubject.getValue());
            selectedTodo.setDate(selectedTodoDate.getValue());
            selectedTodo.setDescription(selectedTodoDescription.getValue());
            selectedTodo.setPriority(priorityListModel.getSelection().iterator().next());
            selectedTodo = todoListService.updateTodo(selectedTodo);
            Clients.showNotification("hey");
        }

    }
    @Listen("onClick = #reloadProfile")
    public void doReloadClick() {
        refreshDetailView();
    }
    //when user checks on the checkbox of each todo on the list
    @Listen("onTodoCheck = #todoListbox")
    public void doTodoCheck(ForwardEvent evt){
        //get data from event
        Checkbox cbox = (Checkbox)evt.getOrigin().getTarget();
        Listitem litem = (Listitem)cbox.getParent().getParent();

        boolean checked = cbox.isChecked();
        TodoList todo = (TodoList) litem.getValue();
        todo.setComplete(checked);

        //save data
        todo = todoListService.updateTodo(todo);
        if(todo.equals(selectedTodo)){
            selectedTodo = todo;
            //refresh detail view
            refreshDetailView();
        }
        //update listitem style
        ((Listitem)cbox.getParent().getParent()).setSclass(checked?"complete":"");
    }
    //when user clicks the delete button of each todo on the list
    @Listen("onTodoDelete = #todoListbox")
    public void doTodoDelete(ForwardEvent evt){
        Button btn = (Button)evt.getOrigin().getTarget();
        Listitem litem = (Listitem)btn.getParent().getParent();

        TodoList todo = (TodoList) litem.getValue();

        //delete data
        todoListService.deleteTodo(todo);

        //update the model of listbox
        todoListModel.remove(todo);

        if(todo.equals(selectedTodo)){
            //refresh selected todo view
            selectedTodo = null;
            refreshDetailView();
        }
    }
}
