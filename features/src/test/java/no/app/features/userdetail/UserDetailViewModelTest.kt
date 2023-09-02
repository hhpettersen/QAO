package no.app.features.userdetail

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import no.app.data.model.api.UserDetailApiModel
import no.app.data.model.api.UserType
import no.app.data.repository.TestUserRepositoryImpl
import no.app.features.util.UiState
import org.junit.After
import org.junit.Before
import org.junit.Test

class UserDetailViewModelTest {

    private val mockUser = UserDetailApiModel(
        login = "xXx_Aragorn_xXx",
        id = 1337,
        avatarUrl = "https://www.google.com/#q=senserit",
        type = UserType.User,
        name = "Aragorn II Elessar",
        company = null,
        location = "Gondor",
        email = "",
    )
    private val repository = TestUserRepositoryImpl(
        users = emptyList(),
        user = mockUser,
    )
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: UserDetailViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = UserDetailViewModel(
            repository,
            SavedStateHandle(mapOf("username" to "xXx_Aragorn_xXx"))
        )
    }

    @Test
    fun `verify UiState emits Success with correct UserDetailApiModel`() = runTest {
        viewModel.uiState.test {
            awaitItem().let { event ->
                val uiState = event as UiState.Success<UserDetailApiModel>
                assertEquals(mockUser, uiState.data)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }
}
