package hr.ferit.leonmaderic.recipeapp.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.text.font.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import hr.ferit.leonmaderic.recipeapp.R
import hr.ferit.leonmaderic.recipeapp.Routes
import hr.ferit.leonmaderic.recipeapp.data.Recipe
import hr.ferit.leonmaderic.recipeapp.data.RecipeViewModel
import hr.ferit.leonmaderic.recipeapp.ui.theme.*

// Saljemo za DZ:
//oba screena (RecipesScreen, RecipeDetailsScreen)
//main activity
//navigation

@Composable
fun RecipesScreen(
    navigation: NavController,
    viewModel: RecipeViewModel,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        ScreenTitle(
            title="What would you like to cook today?",
            subtitle="Good morning, Leon"
        )
        SearchBar(iconResource = R.drawable.ic_search,
            labelText = "Search")
        RecipeCategories()
        RecipeList(recipes = viewModel.recipesData, navigation = navigation)
        IconButton(
            iconResource = R.drawable.ic_plus,
            text = "Add new recipe",
            colors = ButtonDefaults.buttonColors(containerColor = Pink),
            side = 1
        )
    }
}

@Composable
fun ScreenTitle(
    title: String,
    subtitle: String
) {
    Box(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = subtitle,
            style = TextStyle(color = Color.Magenta, fontSize = 12.sp,
                fontWeight = FontWeight.Light, fontStyle = FontStyle.Italic),
            modifier = Modifier.padding(horizontal = 15.dp)
        )
        Text(
            text = title,
            style = TextStyle(color = Color.Black, fontSize = 26.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    @DrawableRes iconResource: Int,
    labelText: String,
    colors: TextFieldColors = TextFieldDefaults.colors(
        unfocusedTextColor = Color.DarkGray,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        unfocusedLabelColor = Color.DarkGray,
        unfocusedPlaceholderColor = Color.DarkGray,
    )
) {
    var searchInput by remember {
        mutableStateOf("")
    }
    TextField(
        value = searchInput,
        onValueChange = { searchInput = it },
        label = {
            Text(labelText)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = iconResource),
                contentDescription = labelText,
                tint = Color.DarkGray,
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
            )
        },
        colors = colors,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}



@Composable
fun TabButton(
    text: String,
    isActive: Boolean,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Button(
        shape = RoundedCornerShape(24.dp),
        elevation = null,
        colors = if (isActive)
                    ButtonDefaults.buttonColors(contentColor = White,containerColor = Pink)
                else
                    ButtonDefaults.buttonColors(contentColor = LightGray, containerColor = Transparent),
        modifier = Modifier.fillMaxHeight(),
        onClick = { onClick() }
    ) {
        Text(text)
    }
}



@Composable
fun RecipeCategories() {
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
            text = "All",
            isActive = currentActiveButton == 0,
            modifier = Modifier.weight(1f),
        ) {
            currentActiveButton = 0
        }
        TabButton(
            text = "Breakfast",
            isActive = currentActiveButton == 1,
            modifier = Modifier
                .weight(1f)
        ){
            currentActiveButton = 1
        }
        TabButton("Lunch", currentActiveButton == 2, Modifier.weight(1f))
        {
            currentActiveButton = 2
        }
    }
}



@Composable
fun IconButton(
    @DrawableRes iconResource: Int,
    text: String,
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = Pink),
    side: Int = 0
) {
    Button(
        onClick = { /*TODO*/ },
        colors = colors,
    ) {
        Row {
            if(side == 0){
                Icon(
                    painter = painterResource(id = iconResource),
                    contentDescription = text
                )
                Spacer(Modifier.width(2.dp))
                Text(
                    text = text,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light
                    )
                )
            }
            else{
                Text(
                    text = text,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light
                    )
                )
                Spacer(Modifier.width(2.dp))
                Icon(
                        painter = painterResource(id = iconResource),
                contentDescription = text
                )
            }
        }
    }
}



@Composable
fun Chip(
    text: String,
    backgroundColor: Color = Color.White,
    textColor: Color = Color.Magenta,
) {
    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = textColor,
                fontSize = 12.sp
            )
        )
    }
}



@Composable
fun RecipeCard(
    imageResource: String,
    title: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .height(326.dp)
            .width(215.dp)
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = LightGray),
            modifier = Modifier
                .padding(bottom = 8.dp)
                .clickable {
                    onClick()
                }
        ) {
            Box {
                Image(
                    painter = rememberAsyncImagePainter(model =
                    imageResource),
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(16.dp)
                ) {
                    Text(
                        text = title,
                        letterSpacing = 0.32.sp,
                        style = TextStyle(
                            color = White,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Row {
                        Chip("30 min")
                        Spacer(Modifier.width(4.dp))
                        Chip("4 ingredients")
                    }
                }
            }
        }
    }
}



@Composable
fun IngredientCard(
    iconResource: String,
    title: String,
    subtitle: String,
){
    Column{
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = White),
            modifier = Modifier.width(100.dp)
                .height(100.dp)
                .padding(8.dp)
        ){
            Image(
                painter = rememberAsyncImagePainter(model = iconResource),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .scale(0.75f, 0.75f)
            )
        }
        Text(text = title,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.width(100.dp)
        )
        Text(text = subtitle,
            color = DarkGray,
            modifier = Modifier.width(100.dp))
    }
}

@Composable
fun RecipeList(
    recipes: List<Recipe>,
    navigation: NavController
) {
    var recipeCount: Int = recipes.size
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "$recipeCount recipes",
                style = TextStyle(
                    color = Color.DarkGray,
                    fontSize = 14.sp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_flame),
                contentDescription = "Flame", tint = Color.DarkGray,
                modifier = Modifier
                    .width(18.dp)
                    .height(18.dp)
            ) }
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
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
            }
        }
    }
}
