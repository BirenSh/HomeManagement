package com.example.homemanagement.ui.theme.screens.home_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.homemanagement.ui.theme.screens.CommonColors
import com.example.homemanagement.utils.CalenderUtil

class HomeScreen(val  userId:String=""):Screen {
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun Content() {
        HomeScreenUi1()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun HomeScreenUi1() {
        val navigator:Navigator? = LocalNavigator.current


        val todayDate = CalenderUtil.getCurrentDate()
        val dateInString = "${todayDate.year} / ${todayDate.month} / ${todayDate.dayOfYear}"

        Scaffold (
            topBar = {
                TopHeader(
                    name = "Birendra",
                    date = dateInString,
                    profileImageUrl = "ic_camera",
                    navigator = navigator)
            },

            bottomBar = {
                HomeScreenBottomBarCompose()
            },

            content = {innerPadding->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    TicketCard(
                        title = "Pending Tickets",
                        count = 10,
                        titleIcon = Icons.Default.Info,
                        iconColor = CommonColors.Orange,
                        description = "Need attention",
                        backgroundColor = CommonColors.OrangeLight,
                        badgeText = "P",
                        badgeColor =  CommonColors.Orange,
                        onItemClick = {
//                            val serializedTicketList = Json.encodeToString(pendingTickets.value)
//                            navigator?.push(AssignedTicketsScreen(serializedTicketList))
                        }
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    TicketCard(
                        title = "Completed Tickets",
                        count = 12,
                        iconColor = CommonColors.BgGreenColor,
                        titleIcon = Icons.Default.Done,
                        description = "This week",
                        backgroundColor = CommonColors.LightGreen,
                        badgeText = "C",
                        badgeColor = CommonColors.BgGreenColor,
                        onItemClick = {
//                            val serializedTicketList = Json.encodeToString(completeTickets.value)
//                            navigator?.push(AssignedTicketsScreen(serializedTicketList))
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    QuickActionsSection()

                }
            }
        )
    }


    @Preview(showSystemUi = true)
    @Composable
    fun PreviewUI() {
        HomeScreenUi1()
    }

}



@Composable
fun TopHeader(
    name: String,
    date: String,
    profileImageUrl: String,
    navigator: Navigator?
) {
    var showModal by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = "Hello, $name", style = MaterialTheme.typography.headlineSmall,
                color = Color.Black)
            Text(text = date, style = MaterialTheme.typography.bodyMedium,
                color = Color.LightGray)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton( onClick = {
//                showToast("App is already updated 😎")
            }) {
                Icon(Icons.Default.Notifications, contentDescription = "Notification")
            }
            Image(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .clickable {
//                        navigator?.push(UserProfileScreen())
                    }
                    .size(40.dp)
                    .clip(CircleShape)
            )
        }
    }
}

@Composable
fun TicketCard(
    title: String,
    count: Int,
    titleIcon: ImageVector,
    iconColor: Color,
    description: String,
    backgroundColor: Color,
    badgeText: String,
    badgeColor: Color,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onItemClick() }
            .shadow(elevation = 5.dp)
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(12.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),) {
                Icon(
                    imageVector = titleIcon,
                    contentDescription = "",
                    tint = iconColor,
                    modifier = Modifier.size(20.dp))
                Text(text = title, color = Color.Black, style = MaterialTheme.typography.titleSmall)
            }
            Text(text = count.toString(), style = MaterialTheme.typography.headlineSmall)
            Text(text = description, style = MaterialTheme.typography.bodyMedium)
        }
        Box(
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(text = badgeText, color = badgeColor, fontWeight = FontWeight.SemiBold)
        }
    }
}


@Composable
fun HomeScreenBottomBarCompose() {

    BottomAppBar(
        contentColor = Color.White,
        containerColor = Color.Black,
        modifier = Modifier
            .clip(shape = RoundedCornerShape(7.dp))
            .padding(16.dp)

    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            BottomBarItems(
                icon = Icons.Default.Home,
                title = "Home",
                description = "stocks",
                onItemClick = {

                }
            )

            BottomBarItems(
                icon = Icons.Default.ShoppingCart,
                title = "Stocks",
                description = "stocks",
                onItemClick = {
//                    navigator?.push(StockScreen())
                }
            )
            BottomBarItems(
                icon = Icons.Default.Settings,
                title = "Setting",
                description = "setting",
                onItemClick = {
//                    navigator?.push(SettingsScreen())
                }
            )
            BottomBarItems(
                icon = Icons.Default.ExitToApp,
                title = "Logout",
                description = "logout",
                onItemClick = {
//                    viewModel.logout()
                },
            )

        }
    }


}



@Composable
fun BottomBarItems(
    onItemClick: () -> Unit,
    icon: ImageVector,
    title: String,
    description: String,
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ){
        Icon(
            imageVector = icon,
            tint = if (icon==Icons.Default.Home) CommonColors.LimeGreen else Color.White,
            contentDescription = description,
        )
        Text(
            text =title,
            color =if (title.equals("Home",true)) CommonColors.LimeGreen else Color.White
        )
    }

}




@Composable
fun QuickActionsSection() {
    Column {
        Text(
            text = "Quick Actions",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            QuickActionButton(
                text = "Person Incharge",
                icon = Icons.Default.DateRange,
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(CommonColors.TextColorGray.copy(alpha = 0.2f)),
                onItemClick = {
//                    val serializedTicketList = Json.encodeToString(ticketList)
//                    navigator?.push(AssignedTicketsScreen(serializedTicketList))
                }
            )

            QuickActionButton(
                text = "Members",
                icon = Icons.Default.Person,
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(CommonColors.TextColorGray.copy(alpha = 0.2f)),
                onItemClick = {
//                    showToast("Yet to come feature...")
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            QuickActionButton(
                text = "Dash Board", icon =
                Icons.Default.Call,
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(CommonColors.TextColorGray.copy(alpha = 0.2f)),
                onItemClick = {
//                    showToast("Yet to come feature...")
                }
            )

            QuickActionButton(
                text = "Reporting Time", icon =
                Icons.Default.LocationOn,
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(CommonColors.TextColorGray.copy(alpha = 0.2f)),
                onItemClick = {
//                    navigator?.push(WebViewScreen())
                }
            )
        }
    }
}




@Composable
fun QuickActionButton(text: String, icon: ImageVector, modifier: Modifier,onItemClick: () -> Unit) {
    Row(
        modifier = modifier
            .clickable { onItemClick() }
            .padding(8.dp)
            .padding(start = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = text,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.labelLarge,
            color = Color.Black
        )
    }
}

