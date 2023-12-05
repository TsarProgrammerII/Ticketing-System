package csci360.TicketingSystem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TicketingController {

    @GetMapping("/")
    public String home() {
        return "index"; // assuming you have an "index.html" in the "src/main/resources/templates" folder
    }
}
