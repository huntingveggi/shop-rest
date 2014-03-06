package de.is.project.shop.gui;

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

import de.is.project.shop.api.domain.Order;
import de.is.project.shop.api.domain.OrderItem;
import de.is.project.shop.api.domain.Product;
import de.is.project.shop.api.domain.ShoppingCart;
import de.is.project.shop.api.services.OrderService;
import de.is.project.shop.api.services.ShoppingCartService;

/**
 * Handles requests for products.
 */
@Controller
@RequestMapping(value = "/shopping-cart")
public class ShoppingCartController {

	private static final Logger logger = LoggerFactory
			.getLogger(ShoppingCartController.class);

	@Inject
	ShoppingCartService shoppingCartService;

	@Inject
	ProductController productController;

	@Inject
	OrderService orderService;

	/**
	 * Handles requests for current shopping cart.
	 * 
	 * @return Returns the current shopping cart
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	ShoppingCart getCurrentOfers() {
		if (this.shoppingCartService.getShoppingCart() == null) {
			throw new NullPointerException("ShoppingCart not found");

		}
		return this.shoppingCartService.getShoppingCart();
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/reset", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	ShoppingCart resetShoppingCart() {
		this.shoppingCartService.resetShoppingCart();
		return this.shoppingCartService.getShoppingCart();
	}

	/**
	 * @param productId
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/remove/{productId}", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	ShoppingCart removeProduct(@PathVariable int productId, Locale locale,
			Model model) {

		Product product = null;
		try {
			product = productController
					.getProductById(productId, locale, model);
			this.shoppingCartService.getShoppingCart();
			this.shoppingCartService.removeProduct(product);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return this.shoppingCartService.getShoppingCart();
	}

	/**
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/create-order", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public @ResponseBody
	Order createOrder(Locale locale, Model model) {

		Order order = orderService
				.createOrderfromShoppingCart(this.shoppingCartService
						.getShoppingCart());
		doLazyInitialization(order);

		return order;

	}

	/**
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/refresh", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public @ResponseBody
	ShoppingCart refresh(Locale locale, Model model) {

		this.shoppingCartService.refreshShoppingCart();

		return this.shoppingCartService.getShoppingCart();
	}

	/**
	 * Handles requests for current shopping cart.
	 * 
	 * @return Returns the current shopping cart
	 */
	/**
	 * @param productId
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add/{productId}", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public @ResponseBody
	ShoppingCart addProduct(@PathVariable int productId, Locale locale,
			Model model) {
		if (this.shoppingCartService.getShoppingCart() == null) {
			throw new NullPointerException("ShoppingCart not found");
		}

		Product product = productController.getProductById(productId, locale,
				model);

		if (product == null) {
			throw new NullPointerException();
		}

		this.shoppingCartService.addProduct(product);
		return this.shoppingCartService.getShoppingCart();
	}

	/**
	 * @param ex
	 */
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Object not found")
	@ExceptionHandler(NullPointerException.class)
	public void handleCustomException(NullPointerException ex) {
		logger.error(ex.getMessage(), ex);
	}

	/**
	 * @param product
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void doLazyInitialization(Order order) {
		order.getItems();
		for (OrderItem item : order.getItems()) {
			productController.doLazyInitialization(item.getProduct());
		}
	}

}
