package be.iccbxl.pid.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import be.iccbxl.pid.model.Type;
import be.iccbxl.pid.model.TypeService;

@Controller
public class TypeController {
	@Autowired
	TypeService service;

	@GetMapping("/types")
    public String index2(Model model) {
		List<Type> types = service.getAll();

		model.addAttribute("types", types);
		model.addAttribute("title", "Liste des types");
		
        return "type/index";
    }
	
	@GetMapping("/types/{id}")
    public String show(Model model, @PathVariable("id") String id) {
		Type type = service.getType(id);

		model.addAttribute("type", type);
		model.addAttribute("title", "Fiche d'un type");
		
        return "type/show";
    }

	@GetMapping("/artists/create")
	public String create(Model model) {
	    Artist artist = new Artist(null,null);

	    model.addAttribute("artist", artist);
		
	    return "artist/create";
	}
	
	@PostMapping("/artists/create")
	public String store(@Valid @ModelAttribute("artist") Artist artist, BindingResult bindingResult, Model model) {
	    
	    if (bindingResult.hasErrors()) {
		return "artist/create";
	    }
		    
	    service.addArtist(artist);
	    
	    return "redirect:/artists/"+artist.getId();
	}

}
