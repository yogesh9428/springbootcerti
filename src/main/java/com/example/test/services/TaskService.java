package com.example.test.services;

import com.example.test.entities.NoteEntity;
import com.example.test.entities.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private NoteService noteService;
    private ArrayList<TaskEntity> tasks = new ArrayList<>();
    private int taskId = 1;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public TaskEntity addTask(String title , String description , String deadline) throws ParseException {
         TaskEntity task = new TaskEntity();
         task.setId(taskId);
         task.setTitle(title);
         task.setDescription(description);
         task.setDeadline(dateFormat.parse(deadline));
         task.setCompleted(false);
         tasks.add(task);
         taskId++;
         return task;
    }

    public ArrayList<TaskEntity> getTasks() {
        return tasks;
    }

    public TaskEntity getTaskById(int id){
//        TaskEntity taskEntity1 = tasks.stream().findAny().filter(taskEntity -> taskEntity.getId() = id).orElse(null);
//        return taskEntity1;
        for (TaskEntity task : tasks){
            if (task.getId() == id){
                return task;
            }
        }
        return null;
    }

    public TaskEntity updateTask(int id ,  String desc , String deadline , Boolean iscomplete) throws ParseException {
        TaskEntity task = getTaskById(id);
        if (task == null) return null;
        if (desc != null) task.setDescription(desc);
        if (deadline != null) task.setDeadline(dateFormat.parse(deadline));
        if (iscomplete != null) task.setCompleted(iscomplete);

        return task;
    }
}
