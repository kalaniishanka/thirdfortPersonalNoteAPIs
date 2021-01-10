package com.project.thirdfort.personalNotes.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.project.thirdfort.personalNotes.model.PersonalNote;

/**
 * Handle archive files.
 * @author Kalani
 *
 */
public class PersonNoteArchiveFile {

    /**
     * Create archive file.
     * 
     * @param personalNote :The note element.
     */
    public static void archiveNote(PersonalNote personalNote) {

	String data = personalNote.getContent();
	String fileNameInZip = personalNote.getId() + ".txt";

	File file = new File("./src/main/resources/notes");

	boolean isFileExist = file.exists();

	if (isFileExist == true) {

	    try (ZipOutputStream zos = new ZipOutputStream(
		    new FileOutputStream("src/main/resources/notes/" + personalNote.getId() + ".zip"))) {

		ZipEntry zipEntry = new ZipEntry(fileNameInZip);
		zos.putNextEntry(zipEntry);

		ByteArrayInputStream bais = new ByteArrayInputStream(data.getBytes());

		// play safe
		byte[] buffer = new byte[1024];
		int len;
		while ((len = bais.read(buffer)) > 0) {
		    zos.write(buffer, 0, len);
		}

		zos.closeEntry();
	    } catch (Exception e) {

		System.out.println(e);
	    }
	} else {
	    System.out.println("Notes folder not exists");

	    if (createNotesFolder()) {
		archiveNote(personalNote);

	    }
	}

    }

    /**
     * Create notes folder.
     * @return {Boolean}
     */
    public static boolean createNotesFolder() {
	
	File file = new File("./src/main/resources/notes");

	boolean bool = file.mkdir();
	if (bool) {
	    System.out.println("Directory created successfully");
	    return true;
	} else {
	    System.out.println("Sorry couldn’t create specified directory");
	    return false;
	}
    }

    /**
     * Delete archive file.
     * @param fileName :Name of the file.
     */
    public static void deleteArchivedNote(String fileName) {

	Path path = FileSystems.getDefault().getPath("./src/main/resources/notes/" + fileName);
	try {
	    Files.delete(path);
	    System.out.println("Archived Note deleted Successfully!");
	} catch (NoSuchFileException x) {
	    System.err.format("%s: no such" + " file or directory%n", path);
	} catch (IOException x) {
	    System.err.println(x);
	}
    }

}
