package itm;

import itm.note.Note;
import itm.note.NoteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GoitDevHw13ApplicationTests {

	NoteService noteService;
	@BeforeEach
	public void beforeEach() {
		noteService = new NoteService();
	}

	@Test
	void contextLoads() {
	}

	@Test
	void noteAddTest() {
		Note note = new Note();
		note.setTitle("Title_1");
		note.setContent("Content_1");
		Note addedNote = noteService.add(note);
		Assertions.assertEquals(note, addedNote);
	}

	@Test
	void deleteByIdTest() {
		Note note = new Note();
		noteService.add(note);
		Assertions.assertDoesNotThrow(()->noteService.deleteById(note.getId()));
	}

	@Test
	void noteUpdateTest() {
		Note note = new Note();
		note.setTitle("Title_1");
		note.setContent("Content_1");
		noteService.add(note);

		Note newNote = new Note();
		newNote.setTitle("newTitle");
		newNote.setContent("newContent");
		newNote.setId(note.getId());
		noteService.update(newNote);
		Assertions.assertEquals(newNote, noteService.getById(note.getId()));
	}

	@Test
	void getByIdTest() {
		Note note = new Note();
		note.setTitle("Title_1");
		note.setContent("Content_1");
		noteService.add(note);
		Note gotNote = noteService.getById(note.getId());
		Assertions.assertEquals(note, gotNote);
	}

	@Test
	void listAllTest() {
		Note note1 = new Note(), note2 = new Note();
		noteService.add(note1);
		noteService.add(note2);

		List<Note> list = noteService.listAll();
		Assertions.assertEquals(7, list.size());
	}

}
