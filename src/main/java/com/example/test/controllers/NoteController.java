package com.example.test.controllers;

import com.example.test.dto.CreateNoteDTO;
import com.example.test.dto.CreateNoteResponseDTO;
import com.example.test.entities.NoteEntity;
import com.example.test.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks/{taskId}/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping("")
    public ResponseEntity<List<NoteEntity>> getNotes(@PathVariable("taskId") Integer taskId){
        var notes =noteService.getNotesForTask(taskId);
        return ResponseEntity.ok(notes);
    }

    @PostMapping("")
    public ResponseEntity<CreateNoteResponseDTO> addNote(@PathVariable("taskId") Integer taskId
    , @RequestBody CreateNoteDTO body) {
            var note = noteService.addNoteForTask(taskId , body.getTitle() , body.getBody());
            return ResponseEntity.ok(new CreateNoteResponseDTO(taskId , note));
    }

}
