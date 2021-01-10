package com.project.thirdfort.personalNotes.model;


import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model class for personal notes. Hold user id and content of the note and
 * achivedStatus field for check archived status of note.
 * 
 * @author Kalani
 *
 */

@Document
public class PersonalNote {

    @Id
    private String id;
    @NotNull(message = "UserId may not be null")
    private String userId;
    @NotNull(message = "Content may not be null")
    private String content;
    private boolean achivedStatus = false;

    public String getUserId() {
	return userId;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public boolean getAchivedStatus() {
	return achivedStatus;
    }

    public void setAchivedStatus(boolean achivedStatus) {
	this.achivedStatus = achivedStatus;
    }

}
