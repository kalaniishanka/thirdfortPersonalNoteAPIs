package com.project.thirdfort.personalNotes.repository;

import java.util.List;

import com.project.thirdfort.personalNotes.model.PersonalNote;

/**
 * @author Kalani
 *
 */
public interface PersonalNoteRepositoryCustom {
  public List<PersonalNote> findByUserId(String userId);

}
