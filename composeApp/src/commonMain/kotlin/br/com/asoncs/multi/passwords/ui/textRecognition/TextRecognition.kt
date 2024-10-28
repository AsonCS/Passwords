package br.com.asoncs.multi.passwords.ui.textRecognition

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncs.multi.passwords.ui.app.AppViewModel
import br.com.asoncs.multi.passwords.ui._navigation.HomeDestination
import br.com.asoncs.multi.passwords.ui.textRecognition.TextRecognition.Args

data object TextRecognition : HomeDestination<Args>(
    "Text Recognition"
) {
    class Args(
        val appViewModel: AppViewModel,
        val navigateUp: () -> Unit
    )

    override operator fun invoke(
        args: Args,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            TextRecognitionScreen(args)
        }
    }
}
