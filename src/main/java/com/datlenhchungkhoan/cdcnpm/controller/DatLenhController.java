package com.datlenhchungkhoan.cdcnpm.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.datlenhchungkhoan.cdcnpm.entity.LenhDat;
import com.datlenhchungkhoan.cdcnpm.service.LenhDatService;

@Controller
public class DatLenhController {
	
	
	@Autowired
	LenhDatService lenhDatService;
	static Logger logger;
	
	
	@GetMapping("/")
	public String showForm(Model model) {
		model.addAttribute("lenh", new LenhDat());
		
		model.addAttribute("list",lenhDatService.layDanhSachLenh());	
		return "index";
	}
	@PostMapping("/")
	public String xuLiLenh(@Valid  @ModelAttribute("lenh") LenhDat lenhDat,BindingResult result, Model model) {
		logger=Logger.getLogger("new logger");
		if (result.hasErrors()) {
			model.addAttribute("list",lenhDatService.layDanhSachLenh());
			return "index";
		}
		lenhDatService.xuLiKhopLenh(lenhDat);
		return "redirect:/";
		
	}
	
}
