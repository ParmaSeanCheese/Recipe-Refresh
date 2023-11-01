package com.launchcode.recipeproject.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Sean Feuerhelm
 */

@Entity
public class Ingredient extends AbstractEntity{

    @NotBlank(message = "*Ingredient name required")
    @Size(max = 255, message = "*Ingredient names must not exceed 50 characters")
    private String name;

    @NotNull(message = "*Quantity required")
    private Double quantity;

    @NotBlank(message = "*Measurement required")
    private String measurement;


    //Added a recipes object to link a many-to-one relationship with recipes
    @ManyToOne (cascade = CascadeType.ALL)
    private Recipe recipe;


    public Ingredient(String name, Double quantity, String measurement) {
        this.name = name;
        this.quantity = quantity;
        this.measurement = measurement;
    }

    public Ingredient() {}


    //Getters and Setters ------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    //Other Methods ------------------------------------------------------

    public Ingredient convertMeasurement(){

        //Volume-----------------------------------------------------------

        //converts tsp to tbsp
        if (this.measurement.equals("tsp") && this.quantity >= 3){
            this.quantity = this.quantity / 3;
            this.measurement =  "tbsp";
        }

        //converts tbsp to cups
        if (this.measurement.equals("tbsp") && this.quantity >= 8){
            this.quantity = this.quantity / 16;
            this.measurement = "cup";
        }

        //converts fluid ounces to cups
        if (this.measurement.equals("fl. oz") && this.quantity >= 8){
            this.quantity = this.quantity / 8;
            this.measurement = "cup";
        }

        //converts cup to pint
        if (this.measurement.equals("cup") && this.quantity >= 4){
            this.quantity = this.quantity / 2;
            this.measurement = "pt";
        }

        //converts pint to quart
        if (this.measurement.equals("pt") && this.quantity >= 2){
            this.quantity = this.quantity / 2;
            this.measurement = "qt";
        }

        //converts quart to gallon
        if (this.measurement.equals("qt") && this.quantity >= 4){
            this.quantity = this.quantity / 4;
            this.measurement = "gal";
        }

        //Mass ---------------------------------------------------------

        //converts ounces to grams
        if (this.measurement.equals("oz") && this.quantity >= 16){
            this.quantity = this.quantity / 16;
            this.measurement = "lb";
        }

        if (this.measurement.equals("g") && this.quantity >= 1000){
            this.quantity = this.quantity / 1000;
            this.measurement = "kg";
        }

        this.quantity = (Math.round(this.quantity*100.00)) / 100.00;

        return this;
    }

    public Ingredient standardizeMeasurement(){

        //volume----------------------------------------------
        if (this.measurement.equals("gal")){
            this.quantity = this.quantity * 768;
            this.measurement = "tsp";
        }

        if (this.measurement.equals("qt")){
            this.quantity = this.quantity * 192;
            this.measurement = "tsp";
        }

        if (this.measurement.equals("pt")){
            this.quantity = this.quantity * 96;
            this.measurement = "tsp";
        }

        if (this.measurement.equals("cup")){
            this.quantity = this.quantity * 48;
            this.measurement = "tsp";
        }

        if (this.measurement.equals("fl. oz")){
            this.quantity = this.quantity * 6;
            this.measurement = "tsp";
        }

        if (this.measurement.equals("tbsp")){
            this.quantity = this.quantity * 3;
            this.measurement = "tsp";
        }

        //Mass -----------------------------------------------------

        if (this.measurement.equals("kg")){
            this.quantity = this.quantity * 1000;
            this.measurement = "g";
        }

        if (this.measurement.equals("lb")){
            this.quantity = this.quantity * 16;
            this.measurement = "oz";
        }

        return this;
    }

    @Override
    public String toString() {
        return name;
    }

}
