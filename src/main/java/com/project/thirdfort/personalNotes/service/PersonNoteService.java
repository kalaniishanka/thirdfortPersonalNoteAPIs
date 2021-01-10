package com.project.thirdfort.personalNotes.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.thirdfort.personalNotes.model.PersonalNote;
import com.project.thirdfort.personalNotes.repository.PersonalNoteRepository;

/**
 * This is a service class to manage personal notes.
 * 
 * @author Kalani
 *
 */

@Service
public class PersonNoteService {

    @Autowired
    private PersonalNoteRepository personalNoteRepository;

    /**
     * Add new note.
     * 
     * @param personalNote: The note element.
     * @return {@link Id}
     */
    public String savePersonalNote(PersonalNote personalNote) {

	PersonalNote result = personalNoteRepository.save(personalNote);
	return "Successfully created!! " + result.getId();
    }

    /**
     * Update note content based on given id in request body.
     * 
     * @param personalNote: The note element.
     * @return {@link Id}
     */
    public String updatePersonalNote(PersonalNote personalNote) {

	String id = personalNote.getId();
	try {
	    Optional<PersonalNote> tempPersonalNote = personalNoteRepository.findById(id);

	    if (tempPersonalNote.isPresent()) {

		PersonalNote castPersonalNote = tempPersonalNote.get();

		castPersonalNote.setContent(personalNote.getContent());

		personalNoteRepository.save(castPersonalNote);

		return "Succesfully Updated " + id;
	    }
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	return "Not Updated " + id;

    }

    /**
     * Delete note and delete archive note if available.
     * 
     * @param personalNote: The note element.
     * @return {@link boolean}
     */
    public String deletePersonalNote(PersonalNote personalNote) {

	String id = personalNote.getId();
	try {
	    Optional<PersonalNote> tempPersonalNote = personalNoteRepository.findById(id);

	    if (tempPersonalNote.isPresent()) {
		PersonalNote castPersonalNote = tempPersonalNote.get();
		personalNoteRepository.deleteById(id);

		if (castPersonalNote.getAchivedStatus()) {
		    PersonNoteArchiveFile.deleteArchivedNote(castPersonalNote.getId() + ".zip");
		}
		return "Succesfully Deleted " + id;
	    }
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	return "Not Delete " + id;
    }

    /**
     * Archive note.
     * 
     * @param personalNote: The note element.
     * @return {@link boolean}
     */
    public String archiveNote(PersonalNote personalNote) {

	String id = personalNote.getId();
	try {
	    Optional<PersonalNote> tempPersonalNote = personalNoteRepository.findById(id);

	    if (tempPersonalNote.isPresent()) {
		PersonalNote castPersonalNote = tempPersonalNote.get();

		if (!castPersonalNote.getAchivedStatus()) {

		    castPersonalNote.setAchivedStatus(true);

		    personalNoteRepository.save(castPersonalNote);

		    PersonNoteArchiveFile.archiveNote(castPersonalNote);
		    return "Succesfully archived " + id;
		}
	    }
	} catch (Exception e) {
	    System.out.println(e.getMessage());

	}

	return "Not archived " + id;
    }

    /**
     * Un-archive previously archived note.
     * 
     * @param personalNote : The note element
     * @return {@link boolean}
     */
    public String unArchiveNote(PersonalNote personalNote) {

	String id = personalNote.getId();
	try {
	    Optional<PersonalNote> tempPersonalNote = personalNoteRepository.findById(id);

	    if (tempPersonalNote.isPresent()) {
		PersonalNote castPersonalNote = tempPersonalNote.get();

		if (castPersonalNote.getAchivedStatus()) {

		    castPersonalNote.setAchivedStatus(false);

		    personalNoteRepository.save(castPersonalNote);

		    PersonNoteArchiveFile.deleteArchivedNote(castPersonalNote.getId() + ".zip");
		}
		return "Succesfully unarchived " + id;
	    }
	} catch (Exception e) {
	    System.out.println(e.getMessage());

	}
	return "Not unarchieved " + id;

    }

    /**
     * Get all archive notes of a user.
     * 
     * @param userId: The user Id string from the request body.
     * @return {@link List} of {@link Notes}
     */
    public List<PersonalNote> getArchivedAll(String userId) {
	try {
	    List<PersonalNote> personalNotes = personalNoteRepository.findByUserId(userId);

	    if (!personalNotes.isEmpty()) {

		List<PersonalNote> noteList = new ArrayList<>();

		for (PersonalNote note : personalNotes) {

		    if (note.getAchivedStatus()) {
			noteList.add(note);
		    }
		}
		return noteList;
	    }
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	return Collections.emptyList();

    }

    /**
     * Get all un-archive notes of a user.
     * 
     * @param userId: The user Id string from the request body.
     * @return {@link List} of {@link Notes}
     */
    public List<PersonalNote> getUnArchivedAll(String userId) {

	List<PersonalNote> personalNotes = personalNoteRepository.findByUserId(userId);
	try {
	    if (!personalNotes.isEmpty()) {
		List<PersonalNote> noteList = new ArrayList<>();

		for (PersonalNote note : personalNotes) {

		    if (!note.getAchivedStatus()) {
			noteList.add(note);
		    }
		}
		return noteList;
	    }
	} catch (Exception e) {
	    System.out.println(e.getMessage());

	}
	return Collections.emptyList();
    }

    /**
     * Get all notes of a user.
     * 
     * @param userId: The user Id string from the request body.
     * @return {@link List} of {@link Notes}
     */
    public List<PersonalNote> getAll(String userId) {
	try {
	    return personalNoteRepository.findByUserId(userId);
	} catch (Exception e) {
	    System.out.println(e.getMessage());

	}
	return Collections.emptyList();

    }

}
