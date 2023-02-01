package com.example.clientapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
    public class ViewController {

        @Autowired
        ItemService itemService;

        @GetMapping("/items")
        public String displayItems(Model model) {
            model.addAttribute("items", itemService.getAllItems());
            return "item-list";
        }

    @RequestMapping("remove/{id}")
    public String romoveItem(@PathVariable(name = "id") Long carId) {
        Item item = itemService.getItem(itemId);
        itemService.saveItem(item);
        return"redirect/cart:";
    }
        
    }
