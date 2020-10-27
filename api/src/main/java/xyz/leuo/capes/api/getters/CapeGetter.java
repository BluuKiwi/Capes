package xyz.leuo.capes.api.getters;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import xyz.leuo.capes.shared.profiles.ProfileManager;

@RestController
public class CapeGetter {

//    @RequestMapping("/capes/{in}")
//    public RedirectView getCape(@PathVariable("in") String in) {
//        return new RedirectView("http://s.optifine.net/capes/" + in);
//    }

    @RequestMapping("/capes/{in}")
    public String getCape(@PathVariable("in") String in) {
        if(in.length() > 4) {
            String name = in.substring(0, in.length() - 4);
            return name;
        } else {
            return null;
        }
    }
}
