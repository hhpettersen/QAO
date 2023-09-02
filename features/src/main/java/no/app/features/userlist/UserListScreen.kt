package no.app.features.userlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import no.app.data.model.api.UserApiModel
import no.app.features.R
import no.app.features.destinations.UserDetailScreenDestination
import no.app.features.userdetail.UserDetailNavArgs
import no.app.features.util.AnimationTransitions

@Destination(style = AnimationTransitions::class)
@Composable
fun UserListScreen(
    navigator: DestinationsNavigator,
) {
    val viewModel = hiltViewModel<UserListViewModel>()
    val users = viewModel.users.collectAsLazyPagingItems()

    UserListContent(
        users = users,
        onUserSelected = {
            navigator.navigate(UserDetailScreenDestination(UserDetailNavArgs(it.login)))
        }
    )
}

@Composable
fun UserListContent(
    users: LazyPagingItems<UserApiModel>,
    onUserSelected: (UserApiModel) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
    ) {
        items(count = users.itemCount, key = users.itemKey { it.id }) { index ->
            users[index]?.let { user ->
                UserListItem(
                    user = user,
                    onUserSelected = onUserSelected
                )
            }
        }
    }
}

@Composable
fun UserListItem(
    user: UserApiModel,
    onUserSelected: (UserApiModel) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onUserSelected(user) }
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape),
            model = user.avatarUrl,
            contentDescription = "Avatar",
            contentScale = ContentScale.FillWidth,
            placeholder = painterResource(id = R.drawable.avatar_placeholder),
            error = painterResource(id = R.drawable.avatar_placeholder)
        )
        Text(text = user.login)
    }
}
