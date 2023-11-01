package com.launchcode.recipeproject.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GroceryList extends AbstractEntity{

    @OneToOne
    private User user;

    private List<Ingredient> shoppingCart = new ArrayList<>();

    public GroceryList(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public List<Ingredient> getShoppingCart() {
        return shoppingCart;
    }

    public void addToShoppingCart(Ingredient newIngredient){
        for (Ingredient ingredient : this.shoppingCart){
            if (ingredient.getName().toLowerCase().equals( newIngredient.getName().toLowerCase() )){
                ingredient.standardizeMeasurement();
                newIngredient.standardizeMeasurement();
                if (ingredient.getMeasurement().equals(newIngredient.getMeasurement())){
                    ingredient.setQuantity(ingredient.getQuantity() + newIngredient.getQuantity() );
                    return;
                }
            }
        }
        this.shoppingCart.add(newIngredient);
    }



}