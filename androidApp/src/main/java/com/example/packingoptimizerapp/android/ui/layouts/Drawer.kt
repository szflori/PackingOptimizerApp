package com.example.packingoptimizerapp.android.ui.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
// import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Drawer(
    drawerState: DrawerState,
    navController: NavController,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(modifier = Modifier.padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())) {
                    Spacer(Modifier.height(12.dp))
                    Text("Optimalizálás", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)
                    // HorizontalDivider()

                    Text("Vágás", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
                    NavigationDrawerItem(
                        label = { Text("1D Vágás") },
                        selected = false,
                        onClick = {
                            navController.navigate("cutting_1d")
                        }
                        )
                }
            }
        }
    ) {
        content()
    }
}