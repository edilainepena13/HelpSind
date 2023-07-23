package com.ufop.HelpSind.controller;

import com.ufop.HelpSind.service.VarApportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("trustee/varApport")
public class VarApportController {

	@Autowired
	private VarApportService varApportService;


	@GetMapping({ "", "/"  })
	public ModelAndView getVarApport(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
			ModelMap model) {
		model.addAttribute("varApport", varApportService.listPage(PageRequest.of(page.orElse(1) - 1, size.orElse(Integer.MAX_VALUE))));
		model.addAttribute("content", "varApportList");
		return new ModelAndView("layouts/trustee", model);
	}



	
	
}
