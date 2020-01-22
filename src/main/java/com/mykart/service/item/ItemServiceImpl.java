package com.mykart.service.item;

import com.mykart.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private RestTemplate template;

    @Override
    public ItemDTO getItem(int item_id) {

        ResponseEntity<ItemDTO> result=template.getForEntity("http://localhost:6060/api/v1/warehouse/"+item_id,ItemDTO.class);
        ItemDTO item=result.getBody();
        System.out.println(item.toString());
        return item;
    }
}
