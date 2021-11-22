package com.example.tufinancieromobileapp.utils.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.navArgument
import com.example.tufinancieromobileapp.data.models.AuthViewModel
import com.example.tufinancieromobileapp.screens.Screen
import com.example.tufinancieromobileapp.screens.composableScreens.MainScreen
import com.example.tufinancieromobileapp.screens.composableScreens.splashScreen
import com.example.tufinancieromobileapp.ui.theme.cardNight
import com.example.tufinancieromobileapp.utils.google.AuthResultContract
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun AuthView(errorText:String?,
             onClick:() -> Unit){
    Scaffold {
        Column(modifier = Modifier.fillMaxSize().background(cardNight), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            GoogleSignInButtonUi(text = "Sign Up With Google",
                loadingText = "Signing In....",
                onClicked = {onClick()})
            errorText?.let {
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = it)
            }
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun AuthScreen(authViewModel: AuthViewModel, navController: NavController){

    val coroutineScope = rememberCoroutineScope()
    var text by remember { mutableStateOf<String?>(null)}
    val user by remember(authViewModel){authViewModel.user}.collectAsState()
    val signInRequestCode = 1

    val authResultLauncher =
        rememberLauncherForActivityResult(contract = AuthResultContract()){
                task ->
            try {
                val account = task?.getResult(ApiException::class.java)
                if (account==null){
                    //text = "Google sign in failed"
                    navController.navigate(Screen.SplasScreen.route)
                }else{
                    coroutineScope.launch {
                        authViewModel.signIn(email = account.email,displayName = account.displayName)
                    }
                }
            }catch (e:ApiException){
                text="Google sign in failed"
            }
        }
    AuthView(errorText = text,onClick = {text=null
        authResultLauncher.launch(signInRequestCode)
    })
    user?.let{
        navController.navigate(Screen.SplasScreen.route)
    }
}