
package com.mykart.service.cart;

import com.mykart.dto.ItemDTO;
import com.mykart.exception.ResourceNotFound;
import com.mykart.model.Cart;
import com.mykart.model.User;
import com.mykart.repository.cart.CartRepository;
import com.mykart.service.item.ItemService;
import com.mykart.service.user.UserService;
import com.mykart.util.CartItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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
    public List<Cart> getAllItems(int user_id) {
        List<ItemDTO> itemDTOList = new ArrayList<>();
        User user=userService.getUserById(user_id);
        String cart_id=user.getCart_id();
        List<Cart> cartList = cartRepository.findByCartIdAndUserId(cart_id,user_id);
       // System.out.println(cartList);
        Iterator<Cart> iterator = cartList.iterator();
        while(iterator.hasNext()){
            itemDTOList.add(itemService.getItem(iterator.next().getItem_id()));

        }
       
        //System.out.println(itemDTOList);
        
        return cartList;
    }

    public ItemDTO getItemById(int user_id, int item_id) throws ResourceNotFound {
        User user=userService.getUserById(user_id);
        ItemDTO item = itemService.getItem(item_id);
        cartRepository.findByCartId(user.getCart_id(),item_id);
        if(!cartRepository.findAll().contains(item))
            throw new ResourceNotFound("resource not found");
        return item;
    }

    @Override
    public Cart saveItem(Cart cart,int user_id) {
        User user=userService.getUserById(user_id);
    if (user != null) {
      System.out.println("arrived at cart service");
      ItemDTO item = itemService.getItem(cart.getItem_id());

      cart.setItem_id(item.getItemId());
      cart.setCart_id(user.getCart_id());
      cart.setPrice(item.getPrice() * cart.getQuantity());
      System.out.println(cart);
      return cartRepository.save(cart);
        }
    else return null;
    }

    @Override
    public String deleteItem(int item_id) throws ResourceNotFound {
        ItemDTO item = itemService.getItem(item_id);
        if(!cartRepository.findAll().contains(item))
            throw new ResourceNotFound("resource not found");
        cartRepository.deleteById(item_id);
        return "deleted successfully";
    }

    @Override
    public Cart updateItemQuantity(Cart cart, int user_id) {
        User user=userService.getUserById(user_id);

        ItemDTO itemDTO = itemService.getItem(cart.getItem_id());
        cart.setPrice(cart.getQuantity()*itemDTO.getPrice());
        cart.setQuantity(cart.getQuantity());
        System.out.println(cart);
        return cartRepository.save(cart);
    }
}
