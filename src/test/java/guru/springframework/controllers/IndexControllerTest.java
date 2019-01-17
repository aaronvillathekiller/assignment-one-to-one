package guru.springframework.controllers;


import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;

import lombok.extern.slf4j.Slf4j;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@Slf4j
public class IndexControllerTest {


    @Mock
    RecipeService recipeService;

    IndexController indexController;


    @Mock
    Model model;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(recipeService);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {

        //Given
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipes.add(recipe);

        when(recipeService.returnRecipeList()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor =  ArgumentCaptor.forClass(Set.class);
        //when
        String testString = indexController.getIndexPage(model);


        //then
        System.out.println(testString);
        assertEquals("index", testString);
        verify(recipeService, times(1)).returnRecipeList();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());

        /**
         * Why is the following method getting an error message saying that String cannot be returned by addAttribute()
         * addAttribute() should return Model?
         *
         * I don't understand the interaction between the addAttribute() and why that is trying to
         * return a string when it is not the method I am calling and not the return result of getIndexPage.
         *
         * Why the fuck is trying to return a String. When the method returns a String?
         *
         * Doesn't matter I did it wrong why did I try and fuck with a working test.
         *
         * If it is not a mock then you do not need to invoke when.
         */
    }




}

