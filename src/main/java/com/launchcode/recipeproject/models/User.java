package com.launchcode.recipeproject.models;

import org.hibernate.annotations.Type;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity{

    private String username;

    private String email;

    private String passwordHash;

    private String roles; // comma separated list of roles "ROLE_USER,ROLE_ADMIN" default is "ROLE_USER"

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // static so all classes can use

    @OneToMany
    private final List<Recipe> recipes = new ArrayList<>();

    @ManyToMany(mappedBy = "menuUsers")
    private final List<Recipe> menuRecipes = new ArrayList<>();

    @ManyToMany
    private final List<Recipe> favoriteList = new ArrayList<>();

    private ArrayList<Ingredient> groceryList = new ArrayList<>();


    public User(){}

    public User(String username, String email, String password, String roles){
        this.username = username;
        this.email = email;
        this.passwordHash = passwordEncoder.encode(password);
        this.roles = roles;
    }

    public Boolean isPasswordMatching(String password){
        return passwordEncoder.matches(password,passwordHash); // can't use .equals because of salting
    }

    public List<Recipe> getFavoriteList() {
        return favoriteList;
    }

    public void setPassword(String password) {
        this.passwordHash = passwordEncoder.encode(password);
    }

    public String getPassword() {
        return passwordHash;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void addRecipe(Recipe recipe){
        this.recipes.add(recipe);
    }

    public void addMenuRecipe(Recipe recipe) { this.menuRecipes.add(recipe);}

    public void removeMenuRecipe(Recipe recipe) { this.menuRecipes.remove(recipe);}

    public void addFavorite(Recipe recipe){
        this.favoriteList.add(recipe);
    }

    public void removeFavoriteRecipe(Recipe recipe) { this.favoriteList.remove(recipe);}

    public List<Recipe> getMenuRecipes() {
        return menuRecipes;
    }

       @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }

    //Grocery List Methods ------------------------------------------------------


    public List<Ingredient> getGroceryList() {
        return groceryList;
    }

    public void setGroceryList(ArrayList<Ingredient> groceryList) {
        this.groceryList = groceryList;
    }

    public void addToGroceryList(Ingredient newIngredient){

        //Checks each ingredient already in the shoppingCart Array to see if the ingredient getting added already exists,
        //and if it does, it then checks that both ingredients are the same type of measurement (mass[imperial/metric] or volume).
        //If both ingredients are of the same measurement type, the quantities are added together and the method is returned.
        //if the loop gets through the whole shopping cart without finding a match, it simply adds it at the end.

        for (Ingredient existingIngredient : this.groceryList){
            if (existingIngredient.getName().toLowerCase().equals( newIngredient.getName().toLowerCase() )){

                Ingredient existingIng = existingIngredient.standardizeMeasurement();
                Ingredient ingToBeAdded = newIngredient.standardizeMeasurement();

                if (existingIng.getMeasurement().equals(ingToBeAdded.getMeasurement())){
                    existingIngredient.setQuantity(existingIng.getQuantity() + ingToBeAdded.getQuantity() );
                    existingIngredient.convertMeasurement();
                    return;
                }
            }
        }
        this.groceryList.add(newIngredient);
    }

}

