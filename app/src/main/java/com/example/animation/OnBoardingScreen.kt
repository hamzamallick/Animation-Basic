package com.example.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun OnBoardingScreen(navHostController: NavHostController) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Visibility Animation", fontWeight = FontWeight.Bold, fontSize = 30.sp)

        Spacer(Modifier.height(20.dp))

        VisibilityExample()

        Spacer(Modifier.height(20.dp))

        Text("Shape Animation", fontWeight = FontWeight.Bold, fontSize = 30.sp)

        Spacer(Modifier.height(20.dp))

        ShapeExample()

        Spacer(Modifier.height(20.dp))

        Text("Transition Animation", fontWeight = FontWeight.Bold, fontSize = 30.sp)

        Spacer(Modifier.height(20.dp))

        TransitionExample()

        Spacer(Modifier.height(20.dp))

        Text("Infinite Animation", fontWeight = FontWeight.Bold, fontSize = 30.sp)

        Spacer(Modifier.height(20.dp))

        InfiniteExample()

        Spacer(Modifier.height(20.dp))

        Text("Shape Animation", fontWeight = FontWeight.Bold, fontSize = 30.sp)

        Spacer(Modifier.height(20.dp))


    }

    AnimatedContentExample()
}

@Composable
fun VisibilityExample() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var toggle by remember {
            mutableStateOf(false)
        }

        Button(onClick = {

            toggle = !toggle

        }) {
            Text("Start Animation", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(20.dp))

        AnimatedVisibility(
            visible = toggle,
            exit = slideOutHorizontally() // we can use this for giving the exit animation
        ) {
            Box(
                Modifier
                    .size(250.dp)
                    .background(Color.LightGray)
            ) {

            }

        }

    }
}

@Composable
fun ShapeExample() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var isRound by remember {
            mutableStateOf(false)
        }

        Button(onClick = {

            isRound = !isRound

        }) {
            Text("Start Animation", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(20.dp))

        val cornerRadius by animateIntAsState(
            targetValue = if (isRound) 85 else 45, label = "",
//            animationSpec = tween(2000)    //this will slow down the effect
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessMedium
            )
        )

        Box(
            modifier = Modifier
                .size(300.dp)
                .clip(RoundedCornerShape(cornerRadius.dp))
                .background(Color.LightGray)
        ) {

        }

    }
}


@Composable
fun TransitionExample() {

    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        var isRound by remember {
            mutableStateOf(false)
        }


        Button(onClick = {

            isRound = !isRound

        }) {
            Text("Start Animation", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(20.dp))

        val transition = updateTransition(targetState = isRound, label = "")

        val cornerRadius by transition.animateInt(
            transitionSpec = { tween(2000) },
            label = "",
            targetValueByState = { isRound ->
                if (isRound) 100 else 0
            }
        )

        val color by transition.animateColor(
            transitionSpec = { tween(3000) },
            label = "",
            targetValueByState = { if (it) Color.Red else Color.Green }
        )

        Box(
            modifier = Modifier
                .size(300.dp)
                .clip(RoundedCornerShape(cornerRadius))
                .background(color)
        ) {

        }

    }

}

@Composable
fun InfiniteExample() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val transition = rememberInfiniteTransition(label = "")

        val color by transition.animateColor(
            initialValue = Color.LightGray,
            targetValue = Color.Yellow,
            animationSpec = infiniteRepeatable(
                animation = tween(2000),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )

        Box(
            modifier = Modifier
                .size(300.dp)
                .background(color)
        ) {

        }


    }
}


@Composable
fun AnimatedContentExample() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        var toggle by remember {
            mutableStateOf(false)
        }

        Button(onClick = {

            toggle = !toggle

        }) {
            Text("Start Animation", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(20.dp))

        AnimatedContent(
            targetState = toggle, modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            label = "",
            content = { toggle ->
                if (toggle) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Yellow)
                    )

                } else {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Red)
                    )

                }


            },
//            transitionSpec = {
//                fadeIn() togetherWith fadeOut()
//            }
            transitionSpec = {
                slideInHorizontally { fullyOut ->
//                    -fullyOut/2 // /2 means animation will start from the middle of the screen

                    if (toggle) -fullyOut else fullyOut

                } togetherWith slideOutHorizontally { fullyIn ->
//                    fullyIn
                    if (toggle) fullyIn else -fullyIn
                }
            }
        )


    }

}