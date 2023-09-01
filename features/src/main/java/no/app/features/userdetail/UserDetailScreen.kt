package no.app.features.userdetail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination

data class UserDetailNavArgs(
    val userId: Int,
)

@Destination(navArgsDelegate = UserDetailNavArgs::class)
@Composable
fun UserDetailScreen() {
    val viewModel = hiltViewModel<UserDetailViewModel>()

    Text(text = "UserDetailScreen")
}

@Composable
fun UserDetailContent(
) {

}
