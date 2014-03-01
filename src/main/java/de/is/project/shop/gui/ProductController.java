package de.is.project.shop.gui;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.is.project.shop.api.domain.Product;
import de.is.project.shop.impl.domain.ProductImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/products")
public class ProductController {

	private static final Logger logger = LoggerFactory
			.getLogger(ProductController.class);

	@RequestMapping(value = "/get", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	List<Product> test(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Product p = new ProductImpl();
		List<Product> products = new LinkedList<Product>();
		products.add(p);
		return products;
	}
}
