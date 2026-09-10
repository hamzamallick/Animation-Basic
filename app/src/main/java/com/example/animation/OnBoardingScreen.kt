package com.example.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.slideOutHorizontally
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

        visibilityExample()

        Spacer(Modifier.height(20.dp))

        Text("Shape Animation", fontWeight = FontWeight.Bold, fontSize = 30.sp)

        shapeExample()
    }
}

@Composable
fun visibilityExample() {

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
fun shapeExample() {

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

        val cornerRadius by animateIntAsState(targetValue = if (isRound) 100 else 0, label = "")

        Box(
            modifier = Modifier
                .size(300.dp)
                .clip(RoundedCornerShape(cornerRadius))
                .background(Color.LightGray)
        ) {

        }

    }
}