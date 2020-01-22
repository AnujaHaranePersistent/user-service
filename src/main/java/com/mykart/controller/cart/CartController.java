package com.mykart.controller.cart;

import com.mykart.dto.ItemDTO;
import com.mykart.model.Cart;
import com.mykart.model.User;
import com.mykart.service.cart.CartService;
import com.mykart.service.item.ItemService;
import com.mykart.validators.Identification;
import io.swagger.annotations.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Log4j2
@Validated
@Api(value = "Cart Data Service",
        description = "Operations pertaining to Cart in Cart Data Service")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{user_id}/cart")
    @ApiOperation(value = "Get list of Users", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")})
    public List<ItemDTO> getAllItems( @ApiParam(value = "User id", required = true) @PathVariable("user_id") @Identification int user_id) {
        log.debug("Executed CartController.getAllItems() to retrieve all Items of  Cart");
        return cartService.getAllItems(user_id);
    }
    @PostMapping("/{user_id}/cart")
    @ApiOperation(value = "Save User object into the database", response = Cart.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")})
    public Cart addItemToCart(
            @ApiParam(value = "User id", required = true) @PathVariable("user_id") @Identification int user_id,
            @ApiParam(value = "Cart object", required = true) @RequestBody Cart cart)
    {
        log.debug("Executed CartController.addItemToCart(cart) to save Item to cart ");
        System.out.println("arrived at cart controller");
        return cartService.saveItem(cart,user_id);
    }

}
