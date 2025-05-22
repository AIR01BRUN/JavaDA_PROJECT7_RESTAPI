package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller handling the home page and admin home page routing.
 */
@Controller
public class HomeController {

	/**
	 * Handles the request for the home page.
	 *
	 * @param model the Spring MVC model
	 * @return the view name for the home page
	 */
	@GetMapping("/")
	public String home(Model model) {
		return "home";
	}

	/**
	 * Handles the request for the admin home page.
	 * Redirects to the bid list page.
	 *
	 * @param model the Spring MVC model
	 * @return a redirect to the bid list page
	 */
	@GetMapping("/admin/home")
	public String adminHome(Model model) {
		return "redirect:/bidList/list";
	}
}
