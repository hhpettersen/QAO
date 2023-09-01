package no.app.features.userlist

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import no.app.data.model.api.UserApiModel

@Destination(start = true)
@Composable
fun UserListScreen() {
    val viewModel = hiltViewModel<UserListViewModel>()

    val users = viewModel.users.collectAsLazyPagingItems()

    UserListContent(users)
}

@Composable
fun UserListContent(
    users: LazyPagingItems<UserApiModel>,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
    ) {
        items(count = users.itemCount, key = users.itemKey { it.id }) { index ->
            users[index]?.let { UserListItem(it) }
        }
    }
}

@Composable
fun UserListItem(
    user: UserApiModel,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape),
            model = user.avatarUrl,
            contentDescription = "Avatar",
            contentScale = ContentScale.FillWidth,
        )
        Text(text = user.login)
    }
}
