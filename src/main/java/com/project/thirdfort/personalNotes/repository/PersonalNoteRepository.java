package com.project.thirdfort.personalNotes.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.thirdfort.personalNotes.model.PersonalNote;



/**
 * Repository class to manage personal notes.
 * @author Kalani
 *
 */
@Repository
public interface PersonalNoteRepository extends MongoRepository<PersonalNote,String>,PersonalNoteRepositoryCustom {
   
    

   
     
    
    	
    }

