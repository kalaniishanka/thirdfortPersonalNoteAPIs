package com.project.thirdfort.personalNotes.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.project.thirdfort.personalNotes.model.PersonalNote;

/**
 * 
 * @author Kalani
 *
 */
public class PersonalNoteRepositoryCustomImpl implements PersonalNoteRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * Find all notes based on userId.
     */
    public List<PersonalNote> findByUserId(String userId) {

	Query query = new Query();

	query.addCriteria(Criteria.where("userId").is(userId));

	return mongoTemplate.find(query, PersonalNote.class);

    }
}
