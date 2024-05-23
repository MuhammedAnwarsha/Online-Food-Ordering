package com.chopze.controller;

import com.chopze.model.IngredientCategory;
import com.chopze.model.IngredientsItem;
import com.chopze.model.User;
import com.chopze.request.IngredientCategoryRequest;
import com.chopze.request.IngredientRequest;
import com.chopze.service.IngredientsService;
import com.chopze.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

    @Autowired
    private IngredientsService ingredientsService;

    @Autowired
    private UserService userService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(@RequestBody IngredientCategoryRequest req,
                                                                       @RequestHeader("Authorization")String jwt)throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        IngredientCategory item = ingredientsService.createIngredientCategory(req.getName(), req.getRestaurantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<IngredientsItem> createIngredientItem(@RequestBody IngredientRequest req,
                                                                @RequestHeader("Authorization")String jwt)throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        IngredientsItem item = ingredientsService.createIngredientItem(req.getRestaurantId(), req.getName(), req.getCategoryId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<IngredientsItem> updateIngredientStock(@PathVariable Long id,
                                                                 @RequestHeader("Authorization")String jwt)throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        IngredientsItem item = ingredientsService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> getRestaurantIngredient(@PathVariable Long id,
                                                                         @RequestHeader("Authorization")String jwt)throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        List<IngredientsItem> items = ingredientsService.findRestaurantsIngredients(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(@PathVariable Long id,
                                                                                 @RequestHeader("Authorization")String jwt)throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        List<IngredientCategory> items = ingredientsService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
