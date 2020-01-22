package com.mykart.service.cart;

import com.mykart.dto.ItemDTO;
import com.mykart.model.Cart;

import java.util.List;

public interface CartService {

    public List<ItemDTO> getAllItems(int user_id);
    public ItemDTO getItemById(int item_id);
    public Cart saveItem(Cart cart,int user_id);
    public void deleteItem(ItemDTO item);
    public ItemDTO updateItemQuantity(ItemDTO item);
}
