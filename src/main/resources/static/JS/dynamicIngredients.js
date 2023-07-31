//Created by Sean Feuerhelm

//Functions for adding

let ingredientCount = 0;

    function addIngredient() {
        let container = document.getElementById("newIngredientContainer");

        // Creates ingredient name text box
        let ingredientName = document.createElement("input");
        ingredientName.type = "text";
        ingredientName.placeholder = "Name";
        ingredientName.setAttribute("id", "ingredients" + ingredientCount + ".name");
        ingredientName.setAttribute("name", "ingredients[" + ingredientCount + "].name");
        container.appendChild(ingredientName);

        // Creates ingredient quantity text box
        let ingredientQuantity = document.createElement("input");
        ingredientQuantity.type = "text";
        ingredientQuantity.placeholder = "Quantity";
        ingredientQuantity.setAttribute("size", "3");
        ingredientQuantity.setAttribute("id", "ingredients" + ingredientCount + ".quantity");
        ingredientQuantity.setAttribute("name", "ingredients[" + ingredientCount + "].quantity");
        container.appendChild(ingredientQuantity);

        // Creates measurement select element
        let measurementUnit = document.createElement("select");
        measurementUnit.setAttribute("id", "ingredients" + ingredientCount + ".measurement");
        measurementUnit.setAttribute("name", "ingredients[" + ingredientCount + "].measurement");

        // Adds options to the select element
        let defaultOption = document.createElement("option");
        defaultOption.text = "unit";
        defaultOption.value = ""
        measurementUnit.appendChild(defaultOption);

        let option1 = document.createElement("option");
        option1.text = "tsp.";
        option1.value = "tsp.";
        measurementUnit.appendChild(option1);

        let option2 = document.createElement("option");
        option2.text = "tbsp.";
        option2.value = "tbsp.";
        measurementUnit.appendChild(option2);

        let option3 = document.createElement("option");
        option3.text = "cup(s)";
        option3.value = "cup";
        measurementUnit.appendChild(option3);

        let option4 = document.createElement("option");
        option4.text = "drop(s)";
        option4.value = "drop";
        measurementUnit.appendChild(option4);

        let option5 = document.createElement("option");
        option5.text = "dash(s)";
        option5.value = "dash";
        measurementUnit.appendChild(option5);

        container.appendChild(measurementUnit);


        // Creates line break after new ingredient
        let linebreak = document.createElement("br");
        container.appendChild(linebreak);

        ingredientCount++;
    }

    addIngredient();

    function removeIngredient() {
        let container = document.getElementById("newIngredientContainer");

        if (ingredientCount > 0) {
            container.removeChild(container.lastElementChild);
            container.removeChild(container.lastElementChild);
            container.removeChild(container.lastElementChild);
            container.removeChild(container.lastElementChild);
            ingredientCount--;
        }
    }