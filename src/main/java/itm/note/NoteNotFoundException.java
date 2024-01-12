package itm.note;

public class NoteNotFoundException extends Exception {
    public static final String NOT_FOUND_MESSAGE = "Note with id=%s was not found";

    public NoteNotFoundException(long id) { super(String.format(NOT_FOUND_MESSAGE, id)); }
}
