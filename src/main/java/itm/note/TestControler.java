package itm.note;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

@Controller
public class TestControler {
    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    public ModelAndView getTime() {
        ModelAndView result = new ModelAndView("test");
        result.addObject("nowJapan", LocalTime.now(ZoneId.of("Japan")));
        result.addObject("now", LocalTime.now());
        result.addObject("nowCanadaEST", LocalTime.now(ZoneId.of("Canada/Eastern")));
        return result;
    }
}