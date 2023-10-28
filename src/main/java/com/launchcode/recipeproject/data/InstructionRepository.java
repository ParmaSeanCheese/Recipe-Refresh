package com.launchcode.recipeproject.data;

import com.launchcode.recipeproject.models.Instruction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructionRepository extends CrudRepository<Instruction, Integer> {
}
