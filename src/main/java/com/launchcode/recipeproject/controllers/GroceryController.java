package com.launchcode.recipeproject.controllers;

import com.launchcode.recipeproject.data.RecipeRepository;
import com.launchcode.recipeproject.data.UserRepository;
import com.launchcode.recipeproject.models.Ingredient;
import com.launchcode.recipeproject.models.Recipe;
import com.launchcode.recipeproject.models.User;
import com.launchcode.recipeproject.services.ControllerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/profile/groceryList")
public class GroceryController {


    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ControllerServices controllerServices;

    @GetMapping
    public String displayGroceryList(Model model, Principal principal){
        User user = controllerServices.getUser(principal);

        if (user == null) {
            model.addAttribute("title", "login");
            return "/login";
        }
        //If user does not have a grocery list set up, this code block will add all the ingredients found on the user's
        //current menu selections to the user's shopping list.
        if (user.getGroceryList() == null){
            user.setGroceryList(new ArrayList<Ingredient>());
        }


        List<Ingredient> groceryList = user.getGroceryList();
        model.addAttribute("groceryList", groceryList);
        return "/profile/groceryList";
    }

    @GetMapping("/populate")
    public String populateGroceryList(Model model, Principal principal){

        User user = controllerServices.getUser(principal);

        if (user == null) {
            model.addAttribute("title", "login");
            return "/login";
        }

        if (user.getGroceryList() == null){
            user.setGroceryList(new ArrayList<Ingredient>());
        }

        for (Recipe recipe : user.getMenuRecipes()) {
            for (Ingredient ingredient : recipe.getIngredientList()) {
             if (!user.getGroceryList().contains(ingredient)) {
                  user.addToGroceryList(ingredient);
               }
           }
        }

       userRepository.save(user);

        List<Ingredient> groceryList = user.getGroceryList();
        model.addAttribute("groceryList", groceryList);
        return "/profile/groceryList";

    }
}
