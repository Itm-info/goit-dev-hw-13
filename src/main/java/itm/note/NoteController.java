package itm.note;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/list")
    public ModelAndView list() {
        List<Note> listOfNotes = noteService.listAll();

        ModelAndView result = new ModelAndView("/note/list");
        result.addObject("notes", listOfNotes);

        return result;
    }

    @PostMapping(value="/delete")
    public String deletePost(@RequestParam Long id) throws NoteNotFoundException {
        noteService.deleteById(id);

        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam Long id) throws NoteNotFoundException {
        Note note = noteService.getById(id);

        ModelAndView result = new ModelAndView("/note/edit");
        result.addObject("id", note.getId());
        result.addObject("title", note.getTitle());
        result.addObject("content", note.getContent());

        return result;
    }

    @PostMapping(value="/edit")
    public String editPost(@RequestParam Long id, @RequestParam String title, @RequestParam String content) throws NoteNotFoundException {
        Note note = noteService.getById(id);
        note.setTitle(title);
        note.setContent(content);
        noteService.update(note);

        return "redirect:/note/list";
    }

    @GetMapping("/create")
    public ModelAndView edit() {
        ModelAndView result = new ModelAndView("/note/create");

        return result;
    }

    @PostMapping(value="/create")
    public String createPost(@RequestParam String title, @RequestParam String content) {
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        noteService.add(note);

        return "redirect:/note/list";
    }
}