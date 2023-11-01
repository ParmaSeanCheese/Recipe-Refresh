package com.launchcode.recipeproject.data;

import com.launchcode.recipeproject.models.GroceryList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryListRepository extends CrudRepository<GroceryList, Integer> {
}
