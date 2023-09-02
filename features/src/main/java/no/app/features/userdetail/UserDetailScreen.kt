package no.app.features.userdetail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import no.app.data.model.api.UserDetailApiModel
import no.app.data.model.api.UserType
import no.app.features.R
import no.app.features.components.GenericErrorState
import no.app.features.components.GenericLoadingState
import no.app.features.components.LabeledText
import no.app.features.util.AnimationTransitions
import no.app.features.util.UiState

data class UserDetailNavArgs(
    val username: String,
)

@Destination(navArgsDelegate = UserDetailNavArgs::class, style = AnimationTransitions::class)
@Composable
fun UserDetailScreen() {
    val viewModel = hiltViewModel<UserDetailViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    UserDetailContent(state = uiState)
}

@Composable
fun UserDetailContent(
    state: UiState<UserDetailApiModel>,
) {
    when (state) {
        is UiState.Error -> GenericErrorState(
            exception = state.exception.message ?: "Unknown error"
        )
        UiState.Loading -> GenericLoadingState()
        is UiState.Success -> {
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically(initialOffsetY = { 40 }),
                exit = slideOutVertically(targetOffsetY = { 40 })
            ) {
                UserDetailSuccessState(user = state.data)
            }
        }
    }
}

@Composable
fun UserDetailSuccessState(
    user: UserDetailApiModel,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopStart),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                AsyncImage(
                    modifier = Modifier
                        .clip(CircleShape)
                        .weight(0.5f),
                    model = user.avatarUrl,
                    placeholder = painterResource(id = R.drawable.avatar_placeholder),
                    error = painterResource(id = R.drawable.avatar_placeholder),
                    contentDescription = "Avatar",
                    contentScale = ContentScale.FillWidth,
                )
                Column(
                    modifier = Modifier.weight(0.5f)
                ) {
                    Text(
                        modifier = Modifier.testTag("Name"),
                        text = user.name ?: "Anonymous",
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Text(
                        modifier = Modifier.testTag("Login"),
                        text = user.login,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
            LabeledText(
                label = "Location:",
                text = user.location ?: "Not available"
            )
            LabeledText(
                label = "Company:",
                text = user.company ?: "Not available"
            )
            LabeledText(
                label = "E-mail:",
                text = user.email ?: "Not available"
            )
        }
    }
}

@Preview
@Composable
fun PreviewUserDetailSuccessState() {
    UserDetailSuccessState(
        user = UserDetailApiModel(
            login = "ramcosta",
            id = 1,
            avatarUrl = "",
            type = UserType.User,
            name = "Ramon Costa",
            company = "LeetHub",
            location = "Oslo, Norway",
            email = "r@m.on",
        )
    )
}
