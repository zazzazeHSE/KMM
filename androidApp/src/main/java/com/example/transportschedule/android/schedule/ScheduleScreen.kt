package com.example.transportschedule.android.schedule

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.transportschedule.android.commonViews.Loader
import com.example.transportschedule.android.commonViews.errorView
import com.example.transportschedule.models.Segment
import com.example.weatherapp.android.R

@Composable
fun ScheduleScreen(
    fromCode: String,
    toCode: String,
    model: ScheduleViewModel = ScheduleViewModel(fromCode = fromCode, toCode = toCode)
) {
    val state by model.stateLiveData.observeAsState(ScheduleState(true, listOf(), null))
    when {
        state.isLoading -> {
            loadingView()
        }
        state.error != null -> {
            errorView(errorText = state.error!!)
        }
        else -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn {
                    items(items = state.schedules) { item ->
                        scheduleView(route = item)
                    }
                }
            }
        }
    }
}

@Composable
fun loadingView() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Loader(text = "Загружаем расписание...")
    }
}

@Composable
fun scheduleView(route: Segment) {
    Card(
        backgroundColor = Color.LightGray,
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp),
        ) {
            Row(

                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 5.dp, vertical = 6.dp)
                    .fillMaxWidth()
            ) {
                Text(text = route.thread.number)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = route.thread.company.title)
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        painter = rememberImagePainter(
                            data = route.thread.company.logo,
                            builder = {
                                transformations(CircleCropTransformation())
                            }
                        ),
                        contentDescription = null,
                        Modifier.size(40.dp)
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = route.arrival)
                Icon(
                    painter = painterResource(id = R.drawable.plane_icon),
                    contentDescription = null
                )
                Text(text = route.departure)
            }
        }
    } 
}
