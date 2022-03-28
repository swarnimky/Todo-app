package com.upstox_demo.todoapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upstox_demo.todoapp.domain.TodoItem;
import com.upstox_demo.todoapp.respository.TodoRepository;

@Service
public class TodoService {
	@Autowired // Inject
	private TodoRepository todoRepo;
	
    public List<TodoItem> fetchAllTodoItems () {
    	return todoRepo.fetchAllTodoItems ();
    }
    public TodoItem updateTodoItem(Integer id , TodoItem todoItem) {
  Optional<TodoItem> todoOpt =  todoRepo.fetchAllTodoItems()
													    .stream()
													    .filter(item -> item.getId().equals(id))
													    .findAny();
       if(todoOpt.isPresent()) {
    	   TodoItem item = todoOpt.get();
    	   item.setIsDone(todoItem.getIsDone());
    	   item.setTask(todoItem.getTask());
    	   return item;
       }
       return null ;
}
	public TodoItem  createTodoItem() {
		 TodoItem todoitem = new TodoItem() ;
		   
		   todoitem.setIsDone(false);
           todoitem = 	todoRepo.save(todoitem);   
		   todoitem.setTask("Task $" + todoitem.getId() );
		   return todoitem;
	}
	public void deleteTodoItem(Integer id) {
          todoRepo.delete(id);		
	}
}
