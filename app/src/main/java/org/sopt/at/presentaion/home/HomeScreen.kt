package org.sopt.at.presentaion.home

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.sopt.at.R
import org.sopt.at.data.remote.model.BottomNavItem
import org.sopt.at.presentaion.components.LiveItemCard
import org.sopt.at.presentation.home.BottomNavBar
import org.sopt.at.presentation.home.HomeViewModel
import org.sopt.at.presentation.home.TopTabBar

@Composable
fun HomeScreen(navController: NavController,homeViewModel: HomeViewModel){
    val context = LocalContext.current
    val liveItems = homeViewModel.liveItems.value
    val bannerItems = homeViewModel.bannerItems.value

    val listState = rememberLazyListState()

    val isScrolled by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 || listState.firstVisibleItemScrollOffset > 0 }
    }
    val bottomNavItems = listOf(
        BottomNavItem(name = "HOME", iconRes = R.drawable.ic_home, selectedIconRes = R.drawable.ic_home),
        BottomNavItem(name = "SHORTS", iconRes = R.drawable.ic_shorts, selectedIconRes = R.drawable.ic_shorts),
        BottomNavItem(name = "LIVE", iconRes = R.drawable.ic_live, selectedIconRes = R.drawable.ic_live),
        BottomNavItem(name = "SEARCH", iconRes = R.drawable.ic_search, selectedIconRes = R.drawable.ic_search),
        BottomNavItem(name = "HISTORY", iconRes = R.drawable.ic_history, selectedIconRes = R.drawable.ic_history)
    )

    var selectedItem by remember { mutableStateOf("HOME") }

    BottomNavBar(
        items = bottomNavItems,
        selectedItem = selectedItem,
        onItemSelected = { selectedItem = it }
    )

    LaunchedEffect(Unit) {
        homeViewModel.fetchLiveItems()
        homeViewModel.fetchBannerItems()
    }


    LazyColumn (
        modifier= Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        state = rememberLazyListState(),


        ){

        item{
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier

            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id=R.drawable.ic_main_logo),
                    contentDescription = "Main logo",
                    modifier= Modifier
                        .size(100.dp)
                        .padding(start = 16.dp)

                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    imageVector = ImageVector.vectorResource(id=R.drawable.ic_subcribe),
                    contentDescription = "subsribe",
                    modifier= Modifier
                        .size(36.dp)
                        .padding(end = 12.dp)
                )
                Image(
                    painter= painterResource(R.drawable.ic_my),
                    contentDescription = "my",
                    modifier= Modifier
                        .size(40.dp)
                        .padding(end = 16.dp)
                        .clickable {
                            navController.navigate("my")
                        }
                )



            }


        }
        item{
            TopTabBar()
        }

        item{
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow (
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier= Modifier
                    .fillMaxWidth()
                    .height(420.dp)
                    .padding(start = 8.dp),



                ){
                items (4){ index ->
                    Box(
                        modifier = Modifier
                            .width(360.dp)
                            .height(420.dp)
                        ,
                    ){
                        Image(
                            painter = painterResource(
                                when (index) {
                                    0 -> R.drawable.img_view_1
                                    1 -> R.drawable.img_view_2
                                    2 -> R.drawable.img_view_3
                                    else -> R.drawable.img_view_4
                                }
                            ),
                            contentDescription = "View ${index + 1}",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(20.dp)),

                            contentScale = ContentScale.Crop

                        )

                    }
                }
            }

        }
        item{
            Spacer(modifier= Modifier.height(24.dp))

            LazyRow (
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier= Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(start = 8.dp),




                ){
                items (5){ index ->
                    Box(
                        modifier = Modifier
                            .width(160.dp)
                            .height(120.dp)
                            .background(color = Color.DarkGray)
                            .clip(RoundedCornerShape(20.dp)),

                        ){
                        Image(
                            painter = painterResource(
                                when (index) {
                                    0 -> R.drawable.ic_banner_1
                                    1 -> R.drawable.ic_banner_2
                                    2 -> R.drawable.ic_banner_3
                                    3 -> R.drawable.ic_banner_4
                                    else -> R.drawable.ic_banner_5
                                }
                            ),
                            contentDescription = "Babber ${index + 1}",
                            modifier = Modifier
                                .fillMaxSize()


                        )

                    }
                }
            }
        }
        item{
            Text(
                modifier= Modifier
                    .padding(start=12.dp, top = 12.dp),
                text="실시간 인기 LIVE",
                color = Color.White

            )
            LazyRow {
                items(liveItems) { liveItem ->
                    LiveItemCard(imageRes = liveItem.imageRes, titleText = liveItem.title)
                }
            }
        }
        item{
            Text(
                text="오늘의 티빙 TOP 20",
                modifier = Modifier
                    .padding(start = 16.dp),
                color = Color.White
            )
            Spacer(modifier= Modifier.height(16.dp))
            LazyRow (
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.height(220.dp)

            ){
                items(4) { index ->
                    Box(
                        modifier = Modifier
                            .width(140.dp)
                            .fillMaxHeight()
                    ) {
                        Image(
                            painter = painterResource(
                                when (index) {
                                    0 -> R.drawable.img_ranking_1
                                    1 -> R.drawable.img_ranking_2
                                    2 -> R.drawable.img_ranking_3
                                    3 -> R.drawable.img_ranking_4
                                    else -> R.drawable.img_ranking_1
                                }
                            ),
                            contentDescription = "Top ${index + 1}",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Text(
                            text = "${index + 1}",
                            color = Color.White,
                            fontSize = 36.sp,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(8.dp)
                        )
                    }
                }

            }

        }
        item{  Spacer(modifier= Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp),

                ){
                Text(
                    text="지금 방영중인 콘텐츠",
                    modifier = Modifier
                        .padding(start=16.dp),
                    color = Color.White,
                )
                Spacer(modifier= Modifier.weight(1f))

                Text(
                    text="더보기",
                    modifier= Modifier
                        .padding(end=16.dp),
                    color = Color.Gray,
                )


            }

        }
        item{
            Spacer(modifier= Modifier.height(8.dp))
            LazyRow (
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .padding(start=16.dp)

            ){

                items(5){ index ->
                    Box(
                        modifier = Modifier
                            .width(160.dp)
                            .fillMaxHeight()

                    ){
                        Image(
                            painter = painterResource(
                                when (index) {
                                    0 -> R.drawable.img_content_1
                                    1 -> R.drawable.img_content_2
                                    2 -> R.drawable.img_content_3
                                    3 -> R.drawable.img_content_4
                                    else -> R.drawable.img_content_5
                                }
                            ),
                            contentDescription = "Content ${index + 1}",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(8.dp)),

                            )


                    }


                }

            }

        }








    }



}
