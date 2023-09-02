package no.app.features

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import no.app.data.model.api.UserDetailApiModel
import no.app.data.model.api.UserType
import no.app.features.userdetail.UserDetailContent
import no.app.features.util.UiState
import org.junit.Rule
import org.junit.Test

class TestPap {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5,
    )

    @Test
    fun launchComposable() {
        paparazzi.snapshot {
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
    }
}
