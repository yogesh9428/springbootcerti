package com.example.test.services;

import com.example.test.entities.NoteEntity;
import com.example.test.entities.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class NoteService {
    @Autowired
    private TaskService taskService;
    private HashMap<Integer , TaskNoteHolder> tasknoteholder = new HashMap<>();

    class TaskNoteHolder{
        protected int noteid = 1;
        protected ArrayList<NoteEntity> notes = new ArrayList<>();
    }

    public NoteEntity addNoteForTask(int taskid , String title , String body){
        TaskEntity task = taskService.getTaskById(taskid);
        if (task == null) return null;
        if (tasknoteholder.get(taskid) == null) tasknoteholder.put(taskid , new TaskNoteHolder());

        TaskNoteHolder holder = tasknoteholder.get(taskid);
        NoteEntity note = new NoteEntity();
        note.setId(holder.noteid);
        note.setTitle(title);
        note.setBody(body);

        holder.notes.add(note);
        holder.noteid++;

        return note;
    }

    public List<NoteEntity> getNotesForTask(int taskid){
        TaskEntity task = taskService.getTaskById(taskid);
        if (task == null) return null;
        if (tasknoteholder.get(taskid) == null) tasknoteholder.put(taskid , new TaskNoteHolder());
        return tasknoteholder.get(taskid).notes;

    }
}
