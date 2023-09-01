package no.app.features.userlist

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun UserListScreen() {
    val viewModel = hiltViewModel<UserListViewModel>()

    Text(text = "User list screen")
}
