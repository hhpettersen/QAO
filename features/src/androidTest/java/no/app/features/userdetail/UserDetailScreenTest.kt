package no.app.features.userdetail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import no.app.data.model.api.UserDetailApiModel
import no.app.data.model.api.UserType
import no.app.features.util.UiState
import org.junit.Rule
import org.junit.Test

class UserDetailScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun initialLoadingStateIsDisplayed() {
        composeTestRule.setContent {
            UserDetailContent(state = UiState.Loading)
        }

        composeTestRule.onNodeWithTag("Loading").assertIsDisplayed()
    }

    @Test
    fun initialErrorStateIsDisplayed() {
        composeTestRule.setContent {
            UserDetailContent(state = UiState.Error(Exception("Major error")))
        }

        composeTestRule.onNodeWithTag("Error").assertIsDisplayed()
        composeTestRule.onNodeWithText("Oh no! Something went wrong! See if you can find the error: Major error").assertIsDisplayed()
    }

    @Test
    fun initialSuccessStateIsDisplayed() {
        composeTestRule.setContent {
            UserDetailContent(
                state = UiState.Success(
                    data = UserDetailApiModel(
                        login = "xXx_Aragorn_xXx",
                        id = 1337,
                        avatarUrl = "https://www.google.com/#q=senserit",
                        type = UserType.User,
                        name = "Aragorn II Elessar",
                        company = null,
                        location = "Gondor",
                        email = "ar@go.rn"
                    )
                )
            )
        }

        composeTestRule.onNodeWithTag("Name").assertTextContains("Aragorn II Elessar")
        composeTestRule.onNodeWithTag("Login").assertTextContains("xXx_Aragorn_xXx")
    }
}
