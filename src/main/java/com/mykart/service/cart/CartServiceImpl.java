package com.mykart.service.cart;

import com.mykart.dto.ItemDTO;
import com.mykart.model.Cart;
import com.mykart.model.User;
import com.mykart.repository.cart.CartRepository;
import com.mykart.service.item.ItemService;
import com.mykart.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;
    
    @Override
    public List<ItemDTO> getAllItems(int user_id) {
        User user=userService.getUserById(user_id);
        String cart_id=user.getCart_id();
        return null;
    }

    @Override
    public ItemDTO getItemById(int item_id) {
        return null;
    }

    @Override
    public Cart saveItem(Cart cart,int user_id) {
        System.out.println("arrived at cart service");
        ItemDTO item=itemService.getItem(cart.getItem_id());
        User user=userService.getUserById(user_id);
        cart.setItem_id(item.getItemId());
        cart.setCart_id(user.getCart_id());
        cart.setPrice(item.getPrice() * cart.getQuantity());
        System.out.println(cart);
        return cartRepository.save(cart);
    }

    @Override
    public void deleteItem(ItemDTO item) {

    }

    @Override
    public ItemDTO updateItemQuantity(ItemDTO item) {
        return null;
    }
}
