//Created by Sean Feuerhelm

//Functions for adding

let initIngContainer = document.getElementById("ingredientContainer");
let ingredientCount = initIngContainer.children.length


    function addIngredient() {
        let container = document.getElementById("newIngredientContainer");
        let ingSpan = document.createElement("span")
        

        // Creates ingredient name text box
        let ingredientName = document.createElement("input");
        ingredientName.type = "text";
        ingredientName.placeholder = "Name";
        ingredientName.setAttribute("id", "ingredients" + ingredientCount + ".name");
        ingredientName.setAttribute("name", "ingredients[" + ingredientCount + "].name");
        ingredientName.setAttribute("class", "ingredient-names")
        ingSpan.appendChild(ingredientName);

        // Creates ingredient quantity text box
        let ingredientQuantity = document.createElement("input");
        ingredientQuantity.type = "text";
        ingredientQuantity.placeholder = "Quantity";
        ingredientQuantity.setAttribute("class", "ingredient-quantity");
        ingredientQuantity.setAttribute("id", "ingredients" + ingredientCount + ".quantity");
        ingredientQuantity.setAttribute("name", "ingredients[" + ingredientCount + "].quantity");
        ingSpan.appendChild(ingredientQuantity);

        // Creates measurement select element
        let measurementUnit = document.createElement("select");
        measurementUnit.setAttribute("class", "ingredient-measurement")
        measurementUnit.setAttribute("id", "ingredients" + ingredientCount + ".measurement");
        measurementUnit.setAttribute("name", "ingredients[" + ingredientCount + "].measurement");

        // Adds options to the select element
        let defaultOption = document.createElement("option");
        defaultOption.text = "unit";
        defaultOption.value = "unit"
        measurementUnit.appendChild(defaultOption);

        let option1 = document.createElement("option");
        option1.text = "whole";
        option1.value = "whole";
        measurementUnit.appendChild(option1);

        let option2 = document.createElement("option");
        option2.text = "tsp.";
        option2.value = "tsp";
        measurementUnit.appendChild(option2);

        let option3 = document.createElement("option");
        option3.text = "tbsp.";
        option3.value = "tbsp";
        measurementUnit.appendChild(option3);

        let option4 = document.createElement("option");
        option4.text = "fl. oz";
        option4.value = "fl. oz";
        measurementUnit.appendChild(option4);

        let option5 = document.createElement("option");
        option5.text = "c.";
        option5.value = "cup";
        measurementUnit.appendChild(option5);

        let option6 = document.createElement("option");
        option6.text = "pt.";
        option6.value = "pt";
        measurementUnit.appendChild(option6);

        let option7 = document.createElement("option");
        option7.text = "qt.";
        option7.value = "qt";
        measurementUnit.appendChild(option7);

        let option8 = document.createElement("option");
        option8.text = "gal.";
        option8.value = "gal";
        measurementUnit.appendChild(option8);

        let option9 = document.createElement("option");
        option9.text = "g.";
        option9.value = "g";
        measurementUnit.appendChild(option9);

        let option10 = document.createElement("option");
        option10.text = "kg.";
        option10.value = "kg";
        measurementUnit.appendChild(option10);

        let option11 = document.createElement("option");
        option11.text = "oz.";
        option11.value = "oz";
        measurementUnit.appendChild(option11);

        let option12 = document.createElement("option");
        option12.text = "lbs.";
        option12.value = "lb";
        measurementUnit.appendChild(option12);

        ingSpan.appendChild(measurementUnit);


        // Creates line break after new ingredient
        let linebreak = document.createElement("br");
        ingSpan.appendChild(linebreak);

        container.appendChild(ingSpan)

        ingredientCount++;
    }

    if (ingredientCount == 0){addIngredient();}


    function removeIngredient() {
        let existingIngredientContainer = document.getElementById("ingredientContainer")
        let newIngredientContainer = document.getElementById("newIngredientContainer");

        if (ingredientCount > 1) {
            if (newIngredientContainer.children.length > 0){
                newIngredientContainer.removeChild(newIngredientContainer.lastElementChild)
            } else if (existingIngredientContainer.children.length > 0){
                existingIngredientContainer.removeChild(existingIngredientContainer.lastElementChild)
            }
            ingredientCount--;
        }
    }

    function removeNewIngredient() {
            let existingIngredientContainer = document.getElementById("ingredientContainer")
            let newIngredientContainer = document.getElementById("newIngredientContainer");

            if (newIngredientContainer.children.length > 0){
                newIngredientContainer.removeChild(newIngredientContainer.lastElementChild)
            }
                ingredientCount--;
    }




    let initInstructionContainer = document.getElementById("instructionContainer")
    let instructionCount = initInstructionContainer.children.length

    function addInstruction(){
        let newInstructionContainer = document.getElementById("newInstructionContainer");
        let instructionSpan = document.createElement("span")

        //Create Number 
        let instructionStep = document.createElement("span");
        instructionStep.innerHTML = instructionCount + 1 + ". ";
        instructionSpan.appendChild(instructionStep);


        //Create Instruction Inputs
        let newInstruction = document.createElement("textarea");
        newInstruction.type = "text";
        newInstruction.setAttribute("id", "instructions" + instructionCount + ".details");
        newInstruction.setAttribute("name", "instructions[" + instructionCount + "].details");
        newInstruction.setAttribute("class", "instructions")
        instructionSpan.appendChild(newInstruction);

        let linebreak = document.createElement("br");
        instructionSpan.appendChild(linebreak);

        newInstructionContainer.appendChild(instructionSpan);
        instructionCount++;
    }

    if (instructionCount == 0){addInstruction();}


    function removeInstruction(){
        let existingInstructionContainer = document.getElementById("instructionContainer");
        let newInstructionContainer = document.getElementById("newInstructionContainer");

        if (instructionCount > 1){
            if (newInstructionContainer.children.length > 0){
                newInstructionContainer.removeChild(newInstructionContainer.lastElementChild);
            } else if (existingInstructionContainer.children.length > 0){
                existingInstructionContainer.removeChild(existingInstructionContainer.lastElementChild);
            }
            instructionCount--;
        }
    }



