package no.app.qao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import no.app.features.NavGraphs
import no.app.qao.ui.theme.QAOTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            QAOTheme {
                Scaffold {
                    DestinationsNavHost(
                        modifier = Modifier.padding(it),
                        navGraph = NavGraphs.root,
                        navController = navController
                    )
                }
            }
        }
    }
}
