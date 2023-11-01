package com.launchcode.recipeproject.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GroceryList extends AbstractEntity{

    @OneToOne
    private User groceryUser;

    private ArrayList<Ingredient> shoppingCart = new ArrayList<>();

    public GroceryList(User groceryUser) {
        this.groceryUser = groceryUser;
    }

    public GroceryList() {
    }

    public User getGroceryUser() {
        return groceryUser;
    }

    public List<Ingredient> getShoppingCart() {
        return shoppingCart;
    }

    public void addToShoppingCart(Ingredient newIngredient){

        //Checks each ingredient already in the shoppingCart Array to see if the ingredient getting added already exists,
        //and if it does, it then checks that both ingredients are the same type of measurement (mass[imperial/metric] or volume).
        //If both ingredients are of the same measurement type, the quantities are added together and the method is returned.
        //if the loop gets through the whole shopping cart without finding a match, it simply adds it at the end.

        for (Ingredient ingredient : this.shoppingCart){
            if (ingredient.getName().toLowerCase().equals( newIngredient.getName().toLowerCase() )){

                Ingredient existingIng = ingredient.standardizeMeasurement();
                Ingredient ingToBeAdded = newIngredient.standardizeMeasurement();

                if (existingIng.getMeasurement().equals(ingToBeAdded.getMeasurement())){
                    ingredient.setQuantity(existingIng.getQuantity() + ingToBeAdded.getQuantity() );
                    ingredient.convertMeasurement();
                    return;
                }
            }
        }
        this.shoppingCart.add(newIngredient);
    }



}