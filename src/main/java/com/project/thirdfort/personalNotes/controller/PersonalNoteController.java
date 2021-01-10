package com.project.thirdfort.personalNotes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.thirdfort.personalNotes.model.PersonalNote;

import com.project.thirdfort.personalNotes.service.PersonNoteService;

/**
 * This is a controller class to manage personal notes.
 * 
 * @author Kalani
 *
 */
@RestController
@RequestMapping("/api/v1/notes")
public class PersonalNoteController {

    @Autowired
    private PersonNoteService personNoteService;

    /**
     * Add new note.
     * 
     * @param personalNote: The note element. *
     * @return {@link Id}
     */
    @PostMapping("/create")
    public String savePersonalNote(@Valid @RequestBody PersonalNote personalNote) {

	return personNoteService.savePersonalNote(personalNote);

    }

    /**
     * Update note content based on given id in request body.
     * 
     * @param personalnote: The note element.
     * @return {@link Id}
     */
    @PutMapping("/update")
    public String updatePersonalNote(@RequestBody PersonalNote personalNote) {

	return personNoteService.updatePersonalNote(personalNote);

    }

    /**
     * Delete note and delete archive note if available.
     * 
     * @param personalNote: The note element.
     * @return {@link Id}
     */
    @DeleteMapping("/deleteById")
    public String deletePersonalNote(@RequestBody PersonalNote personalNote) {

	return personNoteService.deletePersonalNote(personalNote);

    }

    /**
     * Archive note.
     * 
     * @param personalNote: The note element.
     * @return {@link Id}
     */
    @PutMapping("/archiveNote")
    public String archiveNote(@RequestBody PersonalNote personalNote) {

	return personNoteService.archiveNote(personalNote);

    }

    /**
     * Un-archive note.
     * 
     * @param personalnote : The note element.
     * @return {@link String,Id}
     */
    @PutMapping("/unArchiveNote")
    public String unArchiveNote(@RequestBody PersonalNote personalnote) {

	return personNoteService.unArchiveNote(personalnote);

    }

    /**
     * Get all archive notes regarding to given user Id.
     * 
     * @param userId: The user Id string from the request body.
     * @return {@link List} of {@link Notes}
     */
    @GetMapping("/getArchivedAll/{userId}")
    public List<PersonalNote> getArchivedAll(@PathVariable("userId") String userId) {

	return personNoteService.getArchivedAll(userId);

    }

    /**
     * Get all un-archive notes regarding to given user Id.
     * 
     * @param userId:The user Id string from the request body.
     * @return {@link List} of {@link Notes}
     */
    @GetMapping("/getUnArchivedAll/{userId}")
    public List<PersonalNote> getUnArchivedAll(@PathVariable("userId") String userId) {

	return personNoteService.getUnArchivedAll(userId);

    }

    /**
     * Get all notes of a user
     * 
     * @param userId:The user Id string from the request body.
     * @return @link List} of {@link Notes
     */
    @GetMapping("/getAll/{userId}")
    public List<PersonalNote> getAll(@PathVariable("userId") String userId) {
	return personNoteService.getAll(userId);
    }
}
