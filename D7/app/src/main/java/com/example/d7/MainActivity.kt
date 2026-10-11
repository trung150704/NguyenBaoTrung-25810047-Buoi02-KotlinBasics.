package com.example.d7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ContactsAppNavigation()
            }
        }
    }
}

@Composable
fun ContactsAppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "contact_list"
    ) {
        composable("contact_list") {
            ContactListScreen(
                contacts = sampleContacts,
                onContactClick = { contactId ->
                    navController.navigate("contact_detail/$contactId")
                }
            )
        }

        composable(
            route = "contact_detail/{contactId}",
            arguments = listOf(navArgument("contactId") { type = NavType.IntType })
        ) { backStackEntry ->
            val contactId = backStackEntry.arguments?.getInt("contactId")
            val contact = sampleContacts.find { it.id == contactId }

            ContactDetailScreen(
                contact = contact,
                onBackClick = { navController.navigateUp() }
            )
        }
    }
}