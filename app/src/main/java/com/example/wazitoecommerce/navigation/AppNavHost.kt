package com.example.wazitoecommerce.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wazitoecommerce.ui.theme.screens.Complaint.AddComplaintScreen
import com.example.wazitoecommerce.ui.theme.screens.Complaint.ViewComplaintScreen
import com.example.wazitoecommerce.ui.theme.screens.admin.AdminScreen
import com.example.wazitoecommerce.ui.theme.screens.home.HomeScreen
import com.example.wazitoecommerce.ui.theme.screens.login.LoginScreen
import com.example.wazitoecommerce.ui.theme.screens.signup.SignupScreen
import com.example.wazitoecommerce.ui.theme.screens.splash.SplashScreen
import com.example.wazitoecommerce.ui.theme.screens.work.homework
import com.example.wazitoecommerce.ui.theme.screens.work.homework2
import com.example.wazitoecommerce.ui.theme.screens.work.homework3

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController:NavHostController = rememberNavController(),
    startDestination:String = SPLASH_URL
){
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier){
        composable(LOGIN_URL){
            LoginScreen(navController = navController)
        }
        composable(SIGNUP_URL){
            SignupScreen(navController = navController)
        }
        composable(SPLASH_URL){
            SplashScreen(navController = navController)
        }


        composable(LOGIN_URL){
            LoginScreen(navController = navController)
        }

        composable(Screen1_URL){
            homework(navController = navController)
        }

        composable(Screen2_URL){
            homework2(navController = navController)
        }
        composable(Screen3_URL){
            homework3(navController = navController)
        }
        composable(ADMIN_URL){
            AdminScreen(navController = navController)
        }
        composable(HOME_URL){
            HomeScreen(navController = navController)
        }
        composable(ADDCOMPLAINT_URL){
            AddComplaintScreen(navController = navController)
        }
        composable(VIEWCOMPLAINT_URL){
            ViewComplaintScreen(navController = navController)
        }

    }
}