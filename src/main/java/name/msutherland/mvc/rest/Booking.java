package name.msutherland.mvc.rest;

import name.msutherland.dto.Section;
import name.msutherland.dto.Venue;
import name.msutherland.jdbc.SectionDAO;
import name.msutherland.jdbc.VenueDAO;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class Booking {

    private final VenueDAO venueDAO;
    private final SectionDAO sectionDAO;

    public Booking(VenueDAO venueDAO, SectionDAO sectionDAO) {
        this.venueDAO = venueDAO;
        this.sectionDAO = sectionDAO;
    }

    @RequestMapping(path="/venues", method= RequestMethod.GET)
    public List<Venue> venues() {
        return venueDAO.getVenues();
    }

    @RequestMapping(path="/sections", method= RequestMethod.GET)
    public List<Section> sections(@RequestParam(name="selectedVenue") int venueId) {
        return sectionDAO.getSections(venueId);
    }

    @RequestMapping(path="/bookBySeat", method= RequestMethod.POST)
    public ModelMap solve(@Valid BookSpecificSeats form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelMap result = new ModelMap();
            result.mergeAttributes(bindingResult.getModel());
            return result;
        }
        ModelMap result = new ModelMap();
        result.addAttribute("reservationId", 1);
        return result;
    }

    @RequestMapping(path="/bookBySection", method= RequestMethod.POST)
    public ModelMap solve(@Valid BookSection form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelMap result = new ModelMap();
            result.mergeAttributes(bindingResult.getModel());
            return result;
        }
        ModelMap result = new ModelMap();
        result.addAttribute("reservationId", 1);
        return result;
    }
}
