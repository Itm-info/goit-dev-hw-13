package itm.note;

//import jakarta.persistence.*;
import lombok.Data;


@Data
public class Note {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
/*
    public Note() {
        id = new Random().nextLong();
    }*/
}
