package hr.ferit.leonmaderic.recipeapp.data

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import hr.ferit.leonmaderic.recipeapp.R
import hr.ferit.leonmaderic.recipeapp.Routes
import hr.ferit.leonmaderic.recipeapp.ui.*

data class Recipe(
    @DrawableRes val image: Int,
    var title: String,
    var category: String,
    var cookingTime: String,
    var energy: String,
    var rating: String,
    var description: String,
    var reviews: String,
    var ingredients: List<Ingredient>,
    )

data class Ingredient (
    @DrawableRes val image: Int,
    var title: String,
    var subtitle: String,
)



@Composable
fun RecipeList(
    recipes: List<Recipe>,
    navigation: NavController
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "7 recipes",
                style = TextStyle(color = Color.DarkGray, fontSize =
                14.sp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_flame),
                contentDescription = "Flame",
                tint = Color.DarkGray,
                modifier = Modifier
                    .width(18.dp)
                    .height(18.dp)
            )
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            items(recipes.size) {
                RecipeCard(
                    imageResource = recipes[it].image,
                    title = recipes[it].title
                ) {
                    navigation.navigate(
                        Routes.getRecipeDetailsPath(it)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}


val recipes: List<Recipe> = listOf(
    Recipe(
        image = R.drawable.strawberry_pie_1,
        title = "Strawberry Cake",
        category = "Desserts",
        cookingTime = "50 min",
        energy = "620 kcal",
        rating = "4,9",
        description = "This dessert is very tasty and not difficult to prepare. Also, you can replace strawberries with any other berry you like.",
        reviews = "84 photos 430 comments",
        ingredients = listOf(
            Ingredient(R.drawable.flour, "Flour", "450 g"),
            Ingredient(R.drawable.eggs, "Eggs", "4"),
            Ingredient(R.drawable.juice, "Lemon juice", "150 g"),
            Ingredient(R.drawable.strawberry, "Strawberry", "200 g"),
            Ingredient(R.drawable.suggar, "Sugar", "1 cup"),
            Ingredient(R.drawable.mint, "Mint", "20 g"),
            Ingredient(R.drawable.vanilla, "Vanilla", "1/2 teaspoon"),
        )
    ),
    Recipe(
        image = R.drawable.strawberry_pie_1,
        title = "Strawberry Cake2",
        category = "Desserts",
        cookingTime = "50 min",
        energy = "620 kcal",
        rating = "4,9",
        description = "This dessert is very tasty and not difficult to prepare. Also, you can replace strawberries with any other berry you like.",
        reviews = "84 photos 430 comments",
        ingredients = listOf(
            Ingredient(R.drawable.flour, "Flour", "450 g"),
            Ingredient(R.drawable.eggs, "Eggs", "4"),
            Ingredient(R.drawable.juice, "Lemon juice", "150 g"),
            Ingredient(R.drawable.strawberry, "Strawberry", "200 g"),
            Ingredient(R.drawable.suggar, "Sugar", "1 cup"),
            Ingredient(R.drawable.mint, "Mint", "20 g"),
            Ingredient(R.drawable.vanilla, "Vanilla", "1/2 teaspoon"),
        )
    ),
    Recipe(
        image = R.drawable.strawberry_pie_1,
        title = "Strawberry Cake3",
        category = "Desserts",
        cookingTime = "50 min",
        energy = "620 kcal",
        rating = "4,9",
        description = "This dessert is very tasty and not difficult to prepare. Also, you can replace strawberries with any other berry you like.",
        reviews = "84 photos 430 comments",
        ingredients = listOf(
            Ingredient(R.drawable.flour, "Flour", "450 g"),
            Ingredient(R.drawable.eggs, "Eggs", "4"),
            Ingredient(R.drawable.juice, "Lemon juice", "150 g"),
            Ingredient(R.drawable.strawberry, "Strawberry", "200 g"),
            Ingredient(R.drawable.suggar, "Sugar", "1 cup"),
            Ingredient(R.drawable.mint, "Mint", "20 g"),
            Ingredient(R.drawable.vanilla, "Vanilla", "1/2 teaspoon"),
        )
    ),
    Recipe(
        image = R.drawable.strawberry_pie_1,
        title = "Strawberry Cake4",
        category = "Desserts",
        cookingTime = "50 min",
        energy = "620 kcal",
        rating = "4,9",
        description = "This dessert is very tasty and not difficult to prepare. Also, you can replace strawberries with any other berry you like.",
        reviews = "84 photos 430 comments",
        ingredients = listOf(
            Ingredient(R.drawable.flour, "Flour", "450 g"),
            Ingredient(R.drawable.eggs, "Eggs", "4"),
            Ingredient(R.drawable.juice, "Lemon juice", "150 g"),
            Ingredient(R.drawable.strawberry, "Strawberry", "200 g"),
            Ingredient(R.drawable.suggar, "Sugar", "1 cup"),
            Ingredient(R.drawable.mint, "Mint", "20 g"),
            Ingredient(R.drawable.vanilla, "Vanilla", "1/2 teaspoon"),
        )
    )
)