package be.iccbxl.pid.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import be.iccbxl.pid.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import be.iccbxl.pid.model.Show;
import be.iccbxl.pid.model.ShowService;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ShowController {
	@Autowired
	ShowService service;

	@GetMapping("/shows")
    	public String index(Model model) {
		//List<Show> shows = service.getAll();

		//model.addAttribute("shows", shows);
		//model.addAttribute("title", "Liste des spectacles");
		
        	return findPaginated(1, model);
    	}
	
	@GetMapping("/shows/{id}")
   	 public String show(Model model, @PathVariable("id") String id) {
		Show show = service.get(id);

		model.addAttribute("show", show);
		model.addAttribute("title", "Fiche d'un spectacle");
		
        	return "show/show";
    	}

	@GetMapping("/shows/export")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		response.setContentType("text/csv");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=shows_" + currentDateTime + ".csv";
		response.setHeader(headerKey, headerValue);

		List<Show> listShows = service.getAll();

		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		String[] csvHeader = {"ID du spectacle", "Titre", "Description", "Prix", "Date de creation",};
		String[] nameMapping = {"id", "title", "description", "price", "createdAt"};

		csvWriter.writeHeader(csvHeader);

		for (Show show : listShows) {
			csvWriter.write(show, nameMapping);
		}

		csvWriter.close();

	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 2;

		Page< Show > page = service.findPaginated(pageNo, pageSize);
		List < Show > listShows = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("shows", listShows);

		return "show/index";
	}

}
