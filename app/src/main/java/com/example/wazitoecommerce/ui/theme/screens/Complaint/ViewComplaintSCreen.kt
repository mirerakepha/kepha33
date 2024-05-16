package com.example.wazitoecommerce.ui.theme.screens.Complaint


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.wazitoecommerce.R
import com.example.wazitoecommerce.data.ComplaintViewModel

import com.example.wazitoecommerce.models.Complaint


@Composable
fun ViewComplaintScreen(navController:NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var complaintRepository = ComplaintViewModel(navController, context)


        val emptyComplaintState = remember { mutableStateOf(Complaint("","","","","")) }
        var emptyComplaintListState = remember { mutableStateListOf<Complaint>() }

        var complaints = complaintRepository.allComplaints(emptyComplaintState, emptyComplaintListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row {
                Image(painter = painterResource(id = R.drawable.img) ,
                    contentDescription = "",
                    modifier = Modifier.size(80.dp))

                Text(
                    text = "rit",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Magenta,
                    fontFamily = FontFamily.Cursive

                )
            }

            Text(
                text = "Your hostel companion",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif

            )


            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Let's manage your Hostel Things",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif

            )



            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(complaints){
                    ComplaintItem(
                        nature = it.nature,
                        impact = it.impact,
                        location = it.location,
                        id = it.id,
                        navController = navController,
                        complaintRepository = complaintRepository,
                        complaintImage = it.imageUrl
                    )
                }
            }
        }
    }
}


@Composable
fun ComplaintItem(nature:String,impact:String, location:String, id:String,
                navController:NavHostController,
                complaintRepository:ComplaintViewModel, complaintImage:String) {

    Column(modifier = Modifier
        .fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        Row {
            Text(text = "Nature of Complaint :  ", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = nature, modifier = Modifier.padding(top = 10.dp))
        }
        Row {
            Text(text = "Complaint Impact :  ", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = impact, modifier = Modifier.padding(top = 10.dp))
        }

        Row {
            Text(text = "Location of Complaint :  ", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = location, modifier = Modifier.padding(top = 10.dp))
        }


        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
            .height(300.dp), contentAlignment = Alignment.Center) {
            Image(
                painter = rememberAsyncImagePainter(complaintImage),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Button(onClick = {
            complaintRepository.deleteComplaint(id)
        },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(Color.DarkGray),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)) {
            Text(text = "Delete", fontFamily = FontFamily.Monospace)
        }
        Spacer(modifier = Modifier.height(20.dp))


        Divider(modifier = Modifier.background(Color.DarkGray).height(10.dp))

        Spacer(modifier = Modifier.height(20.dp))

    }
}







@Composable
@Preview(showBackground = true)
fun ViewComplaintScreenPreview(){

        ViewComplaintScreen(navController = rememberNavController())

}