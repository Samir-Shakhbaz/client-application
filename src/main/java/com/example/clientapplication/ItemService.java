package com.example.clientapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    @LoadBalanced
    RestTemplate restTemplate;

    public List<Item> getAllItems() {
        ResponseEntity<Item[]> response = restTemplate.getForEntity("http://ITEM-MICROSERVICE/item", Item[].class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        } else {
            return null;
        }
    }

    public Item getItem(){
        ResponseEntity<Item> response = restTemplate.getForEntity("http://ITEM-MICROSERVICE/item", Item.class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return new Item(response.getBody());
        } else {
            return null;
        }
    }

    @GetMapping("/cart")
    public String viewCartItems(Model model) {
        List<Item> itemList = getAllItems();
        model.addAttribute("itemList", itemList);
        return "cart";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

}