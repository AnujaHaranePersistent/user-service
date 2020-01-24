package com.mykart.repository.cart;

import com.mykart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart , Integer> {

    @Query("from Cart where cart_id=?1 ")
    List<Cart> findByCartIdAndUserId(String cart_id, int user_id);

    @Query("from Cart where cart_id=?1 and item_id=?2")
    Cart findByCartId(String cart_id,int item_id);
    
}
