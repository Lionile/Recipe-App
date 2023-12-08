package hr.ferit.leonmaderic.recipeapp.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import hr.ferit.leonmaderic.recipeapp.R
import hr.ferit.leonmaderic.recipeapp.Routes
import hr.ferit.leonmaderic.recipeapp.data.Recipe
import hr.ferit.leonmaderic.recipeapp.data.recipes
import hr.ferit.leonmaderic.recipeapp.ui.theme.*

@Composable
fun RecipeDetailsScreen(
    navigation: NavController,
    recipeId: Int
) {
    val recipe = recipes [recipeId]
    val scrollState = rememberLazyListState()
    LazyColumn(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        state = scrollState,
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            TopImageAndBar(R.drawable.strawberry_pie_1, navigation)
            ScreenInfo(title = "Strawberry Pie", category = "Desserts")
            BasicInfo(recipe)
            Description(recipe)
            Servings()
            IngredientsHeader()
            IngredientsList(recipe)
            ShoppingListButton()
            Reviews(recipe)
            OtherRecipes()
        }
    }
}



@Composable
fun CircularButton(
    @DrawableRes iconResource: Int,
    color: Color = Gray,
    elevation: ButtonElevation? =
        ButtonDefaults.buttonElevation(defaultElevation = 12.dp),
    onClick: () -> Unit = {}
) {
    Button(
        contentPadding = PaddingValues(),
        elevation = elevation,
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = White,
            contentColor = color),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .width(38.dp)
            .height(38.dp)
    ) {
        Icon(
            painter = painterResource(id = iconResource),
            contentDescription = null,
        )
    }
}



@Composable
fun TopImageAndBar(
    @DrawableRes coverImage: Int,
    navigation: NavController,
) {
    Box(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = coverImage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .height(56.dp)
                    .padding(horizontal = 16.dp)
            ) {
                CircularButton(R.drawable.ic_arrow_back){
                    navigation.navigate(Routes.SCREEN_ALL_RECIPES)
                }
                CircularButton(R.drawable.ic_favorite)
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.White
                            ),
                            startY = 100f
                        )
                    )
            )
        }
    }
}



@Composable
fun ScreenInfo(
    title: String,
    category: String
) {
    Column {
        Text(
            text = category,
            style = TextStyle(color = Purple500, fontSize = 15.sp,
                fontWeight = FontWeight.Light),
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
        Text(
            text = title,
            style = TextStyle(color = Color.Black, fontSize = 24.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
    }
}



@Composable
fun InfoColumn(
    @DrawableRes iconResource: Int,
    text: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = iconResource),
            contentDescription = null,
            tint = Pink,
            modifier = Modifier.height(24.dp)
        )
        Text(text = text, fontWeight = FontWeight.Bold)
    }
}



@Composable
fun BasicInfo(recipe: Recipe) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        InfoColumn(R.drawable.ic_clock, recipe.cookingTime)
        InfoColumn(R.drawable.ic_flame, recipe.energy)
        InfoColumn(R.drawable.ic_star, recipe.rating)
    }
}



@Composable
fun Description(
    recipe: Recipe
) {
    Text(
        text = recipe.description,
        fontWeight = FontWeight.Medium,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 20.dp)
    )
}



@Composable
fun <T> EasyGrid(nColumns: Int, items: List<T>, content: @Composable (T) -> Unit) {
    Column(Modifier.padding(16.dp)) {
        for (i in items.indices step nColumns) {
            Row {
                for (j in 0 until nColumns) {
                    if (i + j < items.size) {
                        Box(
                            contentAlignment = Alignment.TopCenter,
                            modifier = Modifier.weight(1f)
                        ) {
                            content(items[i + j])
                        }
                    } else {
                        Spacer(Modifier.weight(1f, fill = true))
                    }
                }
            }
        }
    }
}



@Composable
fun IngredientsList(
    recipe: Recipe
) {
    EasyGrid(nColumns = 3, items = recipe.ingredients) {
        IngredientCard(it.image, it.title, it.subtitle)
    }
}



@Composable
fun ShoppingListButton() {
    Button(
        onClick = { /*TODO*/ },
        elevation = null,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Add to shopping list", modifier =
        Modifier.padding(8.dp))
    }
}



@Composable
fun Reviews(recipe: Recipe) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(start = 16.dp))
    ) {
        Column {
            Text(text = "Reviews", style = TextStyle(fontSize = 16.sp,
                fontWeight = FontWeight.Bold))
            Text(text = recipe.reviews, color = DarkGray)
        }
        IconButton(
            iconResource = R.drawable.ic_arrow_right,
            text = "See all",
            colors = ButtonDefaults.buttonColors(containerColor =
            Transparent, contentColor = Pink),
            side = 1
        )
    }
}



@Composable
fun OtherRecipes() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.strawberry_pie_2),
            contentDescription = "Strawberry Pie",
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(id = R.drawable.strawberry_pie_3),
            contentDescription = "Strawberry Pie",
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
        )
    }
}


@Composable
fun Servings(){
    var value by remember {
        mutableStateOf(0)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(35.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = "Servings", modifier = Modifier.padding(8.dp), fontWeight = FontWeight.Bold)
        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ){
            CircularButton(iconResource = R.drawable.ic_minus, elevation = null, color = Pink){
                value--
            }
            Text(text = "$value")
            CircularButton(iconResource = R.drawable.ic_plus, elevation = null, color = Pink){
                value++
            }
        }
    }
}

@Composable
fun IngredientsHeader() {
    var currentActiveButton by remember { mutableStateOf(0) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .background(Color.Transparent)
            .fillMaxWidth()
            .height(44.dp)
    ) {
        TabButton(
            text = "Ingredients",
            isActive = currentActiveButton == 0,
            modifier = Modifier.weight(1f),
        ) {
            currentActiveButton = 0
        }
        TabButton(
            text = "Tools",
            isActive = currentActiveButton == 1,
            modifier = Modifier
                .weight(1f)
        ){
            currentActiveButton = 1
        }
        TabButton("Steps", currentActiveButton == 2, Modifier.weight(1f))
        {
            currentActiveButton = 2
        }
    }
}