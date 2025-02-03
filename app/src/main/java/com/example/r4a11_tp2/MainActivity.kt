package com.example.r4a11_tp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.r4a11_tp2.ui.theme.R4A11_TP2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            R4A11_TP2Theme {
                MaterialTheme {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        R4A11_TP2Theme {
            Greeting("Android")
        }
    }

    @Composable
    fun AppNavigation() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeScreen(navController = navController)
            }
            composable("form") {
                FormScreen(navController = navController)
            }
            composable(
                route = "display/{name}",
                arguments = listOf(navArgument("name") { defaultValue = ""})
            ) { backStackEntry ->
                val name = backStackEntry.arguments?.getString("name") ?: ""
                AffFormScreen(navController = navController, name)
            }
        }
}

@Composable
fun HomeScreen(navController: NavController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bienvenue dans ma premeire application compose navigation",
            style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { navController.navigate("form")}) {
            Text(text = "Accéder au formulaire")
        }
    }
}

@Composable
fun FormScreen(navController: NavController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Page du formulaire", style = MaterialTheme.typography.titleMedium)

        var name by remember { mutableStateOf("")}
        TextField(
            value = name,
            onValueChange = { newText -> name = newText },
            label = { Text("Entrez votre nom")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { navController.navigate("display/$name")}) {
            Text("Valider")
        }

        Button(
            onClick = { navController.popBackStack() }) {
            Text(text = "Retour")
        }
    }
}

@Composable
fun AffFormScreen(navController: NavController, name : String) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Affichage du formulaire", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(12.dp))
        Text("Bienvenue, $name", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = { navController.popBackStack() }) {
            Text("Retour")
        }
    }
}


