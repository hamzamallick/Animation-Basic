package com.example.animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.animation.ui.theme.AnimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimationTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "Splash") {

                        composable("Splash") {

                            SplashScreen(navHostController = navController)

                        }
                        composable("OnBoarding") {

                            OnBoardingScreen(navHostController = navController)

                        }

                        composable("Home"){

                        }
                    }
                }


            }
        }
    }
}



// This is just the implementation of animation
@Composable
fun MyApp() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {


        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.cute_mascot_jumping_character)
        )

        var isPlaying by remember {
            mutableStateOf(true)
        }

        val progress by animateLottieCompositionAsState(
            composition = composition,
            isPlaying = isPlaying
        )


        LaunchedEffect(progress) {

            if (progress == 0f) {

                isPlaying = true

            }
            if (progress == 1f) {

                isPlaying = false

            }
        }
        LottieAnimation(
            composition = composition,
//            iterations = LottieConstants.IterateForever  (this one is use for Continuously playing the animation)
            modifier = Modifier
                .size(500.dp)
                .clickable {
                    isPlaying = true
                },
            progress = {
                progress
            }
        )
    }
}

