package com.example.passwordmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.passwordmanager.feature_password.presentation.add_edit_password.AddEditPasswordScreen
import com.example.passwordmanager.feature_password.presentation.passwords.PasswordsScreen
import com.example.passwordmanager.feature_password.presentation.util.Screens
import com.example.passwordmanager.ui.theme.PasswordManagerTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PasswordManagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = Screens.PasswordsScreen.route){
                      composable(Screens.PasswordsScreen.route){
                          PasswordsScreen(navController = navController)
                      }

                        composable(Screens.AddEditPasswordScreen.route + "?passwordId={passwordId}",
                        arguments = listOf(
                            navArgument(
                                name = "passwordId",
                            ){
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )){
                            AddEditPasswordScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}