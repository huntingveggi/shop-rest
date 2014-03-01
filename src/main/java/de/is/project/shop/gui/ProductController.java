package de.is.project.shop.gui;

import java.util.Collection;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
	@Transactional(propagation = Propagation.REQUIRED)
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

	@RequestMapping(value = "/{productId}", method = RequestMethod.GET, produces = {
			"application/json", "application/xml" })
	@Transactional(propagation = Propagation.REQUIRED)
	public @ResponseBody
	Product getProductById(@PathVariable int productId, Locale locale,
			Model model) {
		logger.debug("Find product by id: " + productId);
		Product product = productDAO.findById(productId);
		
		if (product != null) {
			product.getCategories().size();
			product.getAttributes().size();

			logger.debug("Found product is: " + product);
			return product;
		}

		throw new NullPointerException("Product with id " + productId
				+ " not found");

	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Object not found")
	@ExceptionHandler(NullPointerException.class)
	public void handleCustomException(NullPointerException ex) {
	}

}
