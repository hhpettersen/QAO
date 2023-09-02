package no.app.features.login

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import no.app.features.R
import no.app.features.components.GenericErrorState
import no.app.features.components.GenericLoadingState
import no.app.features.destinations.LoginScreenDestination
import no.app.features.destinations.UserListScreenDestination
import no.app.features.util.AnimationTransitions
import no.app.features.util.UiState

@RootNavGraph(start = true)
@Destination(style = AnimationTransitions::class)
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
) {

    val viewModel = hiltViewModel<LoginViewModel>()
    val context = LocalContext.current
    val authLauncher = rememberFirebaseAuthLauncher(
        // Since I don't pass in a valid server client id, the auth will fail, faking "success" for the purpose of this project.
        onAuthComplete = {
            viewModel.onAuthenticated()
        },
        onAuthError = {
            viewModel.onAuthenticated()
        }
    )

    val state = viewModel.uiState.collectAsState()

    LoginContent(
        state = state.value,
        // Not pleased with this solution, would like to discuss this further.
        onAuthComplete = {
            navigator.navigate(direction = UserListScreenDestination) {
                popUpTo(LoginScreenDestination) {
                    inclusive = true
                }
            }
        },
        onLoginClick = {
            val gso =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    // Left blank to avoid storing users in firestore database at the current state of the project.
                    .requestIdToken(":)")
                    .requestEmail()
                    .build()
            val googleSignInClient = GoogleSignIn.getClient(context, gso)
            authLauncher.launch(googleSignInClient.signInIntent)
        }
    )
}

@Composable
fun LoginContent(
    state: UiState<LoginState>,
    onAuthComplete: () -> Unit,
    onLoginClick: () -> Unit,
) {
    when (state) {
        is UiState.Error -> {
            GenericErrorState(
                exception = state.exception.message ?: "Unknown error"
            )
        }
        UiState.Loading -> {
            GenericLoadingState()
        }
        is UiState.Success -> {
            if (state.data.isAuthenticated) {
                onAuthComplete()
            } else {
                LoginButton(
                    onLoginClick = onLoginClick
                )
            }
        }
    }
}

@Composable
fun LoginButton(
    onLoginClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Button(
                onClick = { onLoginClick() }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google_icon),
                    contentDescription = "Google icon",
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Sign in via Google")
            }
        }
    }
}

@Preview
@Composable
fun PreviewLoginContent() {
    LoginContent(
        state = UiState.Success(LoginState(false)),
        onAuthComplete = { /*TODO*/ },
        onLoginClick = { /*TODO*/ }
    )
}

@Composable
fun rememberFirebaseAuthLauncher(
    onAuthComplete: (AuthResult) -> Unit,
    onAuthError: (ApiException) -> Unit
): ManagedActivityResultLauncher<Intent, ActivityResult> {
    val scope = rememberCoroutineScope()
    return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
            scope.launch {
                val authResult = Firebase.auth.signInWithCredential(credential).await()
                onAuthComplete(authResult)
            }
        } catch (e: ApiException) {
            onAuthError(e)
        }
    }
}
