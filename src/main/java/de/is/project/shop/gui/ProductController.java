package de.is.project.shop.gui;

import java.util.Collection;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.is.project.shop.api.domain.Product;
import de.is.project.shop.api.persistence.ProductDAO;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/products")
public class ProductController {

	private static final Logger logger = LoggerFactory
			.getLogger(ProductController.class);

	@Inject
	ProductDAO productDAO;

	@RequestMapping(value = "/current-offers", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	Collection<Product> getCurrentOfers(Locale locale, Model model) {
		Collection<Product> products = productDAO.getCurrentOffers();
		logger.debug("product.size " + products.size());
		for (Product product : products) {
			logger.debug(product.getId() + " - " + product.getName() + " - "
					+ product.getDescription());
		}
		products.add(productDAO.getNewInstance());
		return products;
	}
}
