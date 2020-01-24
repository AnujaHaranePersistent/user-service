package com.mykart.service.cart;

import com.mykart.dto.ItemDTO;
import com.mykart.exception.ResourceNotFound;
import com.mykart.model.Cart;

import java.util.List;

public interface CartService {
    public List<Cart> getAllItems(int user_id);
    public ItemDTO getItemById(int user_id,int item_id) throws ResourceNotFound;
    public Cart saveItem(Cart cart,int user_id);
    public String deleteItem(int item_id) throws ResourceNotFound;
    public Cart updateItemQuantity(Cart cart, int user_id);
   
}
