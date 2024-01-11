package itm.note;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.commons.lang3.RandomStringUtils;


import static java.util.Objects.isNull;

@Service
public class NoteService {

    private final ConcurrentMap<Long, Note> notes = new ConcurrentHashMap<>();

    public NoteService () {
        int i=0;
        while ( ++i <= 5 )
            this.add(genRandomNote());

    }

    public List<Note> listAll() {
        List<Note> res = new ArrayList<>();
        if( ! notes.isEmpty() ) notes.forEach((l,n) -> res.add(n));
        return res;
    }

    public Note add(Note note) {
        Random random = new Random();
        Note prevNote;
        long id;

        do {
            id = Math.abs(random.nextLong());
            note.setId(id);
            prevNote = notes.putIfAbsent(id, note);
        } while ( (!isNull(prevNote)) && !prevNote.equals(note) );

        return note;
    }

    public void deleteById(long id) throws NullPointerException {
        notes.remove(id);
    }

    public void update(Note note) throws NullPointerException {
        notes.computeIfPresent( note.getId(), (i,n) -> note );
    }

    public Note getById(long id) throws NullPointerException {
        return notes.get(id);
    }

    private Note genRandomNote() {
        Note note = new Note();
        note.setTitle(RandomStringUtils.randomAlphabetic(5));
        note.setContent(RandomStringUtils.randomAlphabetic(20));
        return note;
    }
}
