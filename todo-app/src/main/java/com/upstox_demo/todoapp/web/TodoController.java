package com.upstox_demo.todoapp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.upstox_demo.todoapp.domain.TodoItem;
import com.upstox_demo.todoapp.service.TodoService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
//Access-Control-Allow-Origin: https://localhost:3000
public class TodoController {
	
	//front-end            java-server
	//HttpRequest --->  Controller --->  Service ---> Repository 
	//front-end      <---  Controller <---  Service  <---                
	@Autowired
	private TodoService todoService;

	/*
	 * CRUD
	 * Create - POST  = 'http://localhost:8080/api/domainObjectName'
	 * Read - GET =   'http://localhost:8080/api/domainObjectName' OR
	 *                       'http://localhost:8080/api/domainObjectName/{id}'
	 * Update -PUT =  'http://localhost:8080/api/domainObjectName/{id}'
	 * Delete - DELETE = 'http://localhost:8080/api/domainObjectName/{id}'
	 */
	//fetch all todo items from databases
	
   @GetMapping("/api/todoItems")
   public ResponseEntity<?> fetchAllTodoItems() {
	  List<TodoItem> todoItems = todoService.fetchAllTodoItems () ;

	  return ResponseEntity.status(HttpStatus.OK).body(todoItems);
    }
    
   @PostMapping("/api/todoItems")
   public ResponseEntity<?> createNewTodoItem () {
	   TodoItem todoItem = todoService.createTodoItem();
	  
	   return ResponseEntity.status(HttpStatus.OK).body(todoItem);
   }
         
   @PutMapping("/api/todoItems/{id}")
   public ResponseEntity<?> updateTodoItem( @PathVariable  Integer id , @RequestBody TodoItem todoItem) {
        //call the service
	   //get the data back from server
	  TodoItem updatedTodoItem = todoService.updateTodoItem( id , todoItem );  		 
	    //send it back to frontend  
	  return ResponseEntity.ok(updatedTodoItem);
   }
	  @DeleteMapping("/api/todoItems/{id}")	
		  public ResponseEntity<?> deleteTodoItem( @PathVariable  Integer id ) {
		        //call the service
			   //get the data back from server
			 todoService.deleteTodoItem( id  );  		 
			    //send it back to frontend  
			  return ResponseEntity.ok("ok");
		   
	  }
}

