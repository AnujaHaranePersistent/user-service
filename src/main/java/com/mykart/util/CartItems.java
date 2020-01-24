package com.mykart.util;

import com.mykart.dto.ItemDTO;
import com.mykart.model.Cart;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartItems {

    private int itemId;
    private String itemName;
    private String sellerInfo;
    private String category;
    private int inStock;
    private double price;
    private int quantity;

}
