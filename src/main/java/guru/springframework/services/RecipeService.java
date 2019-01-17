package guru.springframework.services;

import guru.springframework.domain.Recipe;

import java.util.HashSet;
import java.util.Set;

public interface RecipeService {
    public Set<Recipe> returnRecipeList();
}
