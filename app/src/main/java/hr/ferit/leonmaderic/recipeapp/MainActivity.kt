package hr.ferit.leonmaderic.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import hr.ferit.leonmaderic.recipeapp.data.Ingredient
import hr.ferit.leonmaderic.recipeapp.data.Recipe
import hr.ferit.leonmaderic.recipeapp.data.RecipeViewModel
import hr.ferit.leonmaderic.recipeapp.ui.RecipeDetailsScreen
import hr.ferit.leonmaderic.recipeapp.ui.RecipesScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<RecipeViewModel>()
        setContent {
            NavigationController(viewModel)
        }
    }
}