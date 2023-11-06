package com.launchcode.recipeproject.controllers;

import com.launchcode.recipeproject.data.RecipeRepository;
import com.launchcode.recipeproject.data.UserRepository;
import com.launchcode.recipeproject.models.Recipe;
import com.launchcode.recipeproject.models.User;
import com.launchcode.recipeproject.services.ControllerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/profile/favorites")
public class FavoriteController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ControllerServices controllerServices;

    @GetMapping
    public String displayFavorites(Model model, Principal principal){
        User user = controllerServices.getUser(principal);

        if (user == null){
            model.addAttribute("title", "login");
            return "/login";
        }

        List<Recipe> favoriteRecipes = user.getFavoriteList();
        model.addAttribute("favoriteRecipes", favoriteRecipes);
        return "/profile/favorites";
    }


//    @GetMapping("add")
//    public String displayAddFavoriteForm(Model model) {
//        model.addAttribute("title", "Add Favorite");
//        model.addAttribute(new Favorite());
//        model.addAttribute("recipes", recipeRepository.findAll());
//        return "profile/favorites/add";
//    }
//
//
//    @PostMapping("add")
//    public String processAddFavoriteForm(@ModelAttribute @Valid Favorite newFavorite, Principal principal,
//                                         Errors errors, Model model) {
//        User user= controllerServices.getUser(principal);
//        if (user == null) {
//            model.addAttribute("title", "login");
//            return "/login";
//        }
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Add Favorite");
//            model.addAttribute("recipes", recipeRepository.findAll());
//            return "profile/favorites/add";
//        }
//        user.addFavorite(newFavorite);
//        favoriteRepository.save(newFavorite);
//        return "redirect:";
//
//    }
//
//    @GetMapping("")
//    public String displayMyFavorites(Model model, Principal principal){
//        User user = controllerServices.getUser(principal);
//        if (user == null) {
//            model.addAttribute("title", "login");
//            return "/login";
//        }
//        int userId = user.getId();
//        model.addAttribute("title", " My Favorite Recipes");
//        model.addAttribute("favorites", favoriteRepository.findByUserId(userId));
//        return "profile/favorites/index";
//    }
//    @PostMapping("add/Favorite")
//    public String addFavorite(@ModelAttribute Favorite favorite, Principal principal){
//        Optional<User> optionalUser = userRepository.findByUsername(principal.getName());
//        Recipe recipe= favorite.getRecipe();
//
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//
//            favorite.setUser(user);
//            favorite.setRecipe(recipe);
//
//            recipe.getFavoriteUsers().add(favorite);
//            user.getFavoriteList().add(favorite);
//
//            recipeRepository.save(recipe);
//            favoriteRepository.save(favorite);
//            userRepository.save(user);
//        }
//
//        return "redirect:/";
//    }

}






