package com.example.test.controllers;

import com.example.test.dto.CreateTaskDTO;
import com.example.test.dto.ErrorHandleDTO;
import com.example.test.dto.TaskResponseDTO;
import com.example.test.dto.UpdateTaskDTO;
import com.example.test.entities.TaskEntity;
import com.example.test.services.NoteService;
import com.example.test.services.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private NoteService noteService;

    private ModelMapper modelMapper = new ModelMapper();



    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> gettasks(){
        var tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable("id") Integer id){
        var task = taskService.getTaskById(id);
        var notes = noteService.getNotesForTask(id);
        if (task == null){
            return ResponseEntity.notFound().build();
        }
        var response = modelMapper.map(task , TaskResponseDTO.class);
        response.setNotes(notes);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body) throws ParseException {
        var task = taskService.addTask(body.getTitle() , body.getDescription() , body.getDeadline());
        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<ErrorHandleDTO> handleErros(Exception e){
        if (e instanceof ParseException)
            return ResponseEntity.badRequest().body(new ErrorHandleDTO("Invalid Date Format"));

        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new ErrorHandleDTO("Internal Server Error"));
    }

    @PatchMapping("{id}")
    public ResponseEntity<TaskEntity> updatetask(@PathVariable("id") Integer id , @RequestBody UpdateTaskDTO body) throws ParseException {
        var task = taskService.updateTask(id , body.getDescription() , body.getDeadline(), body.getIscomplete());
        if (task == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(task);
    }


}
