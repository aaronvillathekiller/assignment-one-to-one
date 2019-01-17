package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReturnRecipeServiceTest {


    ReturnRecipeService returnRecipeService;

    @Mock
    RecipeRepository recipeRepository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        returnRecipeService = new ReturnRecipeService(recipeRepository);
    }

    @Test
    public void returnRecipeList() throws NoSuchElementException {

        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();
        recipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = returnRecipeService.returnRecipeList();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();

    }
}