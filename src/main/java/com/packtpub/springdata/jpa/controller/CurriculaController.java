package com.packtpub.springdata.jpa.controller;


import java.util.List;

import javax.swing.text.html.FormSubmitEvent.MethodType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.packtpub.springdata.jpa.model.Curriculum;
import com.packtpub.springdata.jpa.service.CurriculumService;

@Controller
@RequestMapping(value="/curricula")
public class CurriculaController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CurriculaController.class);
	private static final String CURRICULA_HOME_VIEW = "curricula";
	
	@Autowired
	private CurriculumService curriculumService;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<Curriculum> getCurricula() {
		List<Curriculum> curricula = curriculumService.getCurricula();
		return curricula;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Curriculum getCurriculum(@PathVariable(value="id") Long id) {
		return curriculumService.getCurriculum(id);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public void deleteCurriculum(@PathVariable(value="id") Long id) {
		curriculumService.deleteCurriculum(id);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Curriculum createCurriculum(@RequestBody Curriculum curriculum) {
		return curriculumService.createUpdateCurriculum(curriculum);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public Curriculum updateCurriculum(@RequestBody Curriculum curriculum) {
		return curriculumService.createUpdateCurriculum(curriculum);
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	@ResponseBody
	public List<Curriculum> searchCurricula(@RequestParam(value="key", required = false) String key) {
		List<Curriculum> curricula = curriculumService.searchCurricula(key);
		return curricula;
	}
	
	@RequestMapping(value="/home")
	public String showCurriculaHomePage(Model model) {
		LOGGER.debug("showing curricula home page...");
		return CURRICULA_HOME_VIEW;
	}
}
