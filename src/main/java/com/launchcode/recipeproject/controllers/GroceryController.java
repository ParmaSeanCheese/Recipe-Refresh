package com.launchcode.recipeproject.controllers;

import com.launchcode.recipeproject.data.GroceryListRepository;
import com.launchcode.recipeproject.data.IngredientRepository;
import com.launchcode.recipeproject.data.RecipeRepository;
import com.launchcode.recipeproject.data.UserRepository;
import com.launchcode.recipeproject.models.GroceryList;
import com.launchcode.recipeproject.models.Ingredient;
import com.launchcode.recipeproject.models.Recipe;
import com.launchcode.recipeproject.models.User;
import com.launchcode.recipeproject.services.ControllerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class GroceryController {


    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroceryListRepository groceryListRepository;

    @Autowired
    ControllerServices controllerServices;

    @GetMapping("profile/groceryList")
    public String displayGroceryList(Model model, Principal principal){
        User user = controllerServices.getUser(principal);

        if (user == null) {
            model.addAttribute("title", "login");
            return "/login";
        }
        //If user does not have a grocery list set up, this code block will add all the ingredients found on the user's
        //current menu selections to the user's shopping list.
        if (user.getGroceryList() == null){
            user.setGroceryList(new GroceryList(user));
            for (Recipe recipe : user.getMenuRecipes()){
                for (Ingredient ingredient : recipe.getIngredientList()){
                    user.getGroceryList().addToShoppingCart(ingredient);
                }
            }
            userRepository.save(user);
        }

        List<Ingredient> shoppingCart = user.getGroceryList().getShoppingCart();
        model.addAttribute("shoppingCart", shoppingCart);
        return "/profile/groceryList";
    }
}
