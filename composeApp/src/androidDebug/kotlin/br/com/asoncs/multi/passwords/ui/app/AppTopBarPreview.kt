package br.com.asoncs.multi.passwords.ui.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.asoncs.multi.passwords.ui.PreviewContainer
import br.com.asoncs.multi.passwords.ui.PreviewPixel7

@PreviewPixel7
@Composable
private fun AppTopBarPreview(
    @PreviewParameter(ValuesProvider::class) state: AppTopBarState
) {
    PreviewContainer {
        AppTopBar(
            handlerBack = state.handlerBack,
            handlerUser = state.handlerUser,
            modifier = Modifier,
            userName = "John Doe",
            userPhotoUrl = null
        )
    }
}

private class ValuesProvider : PreviewParameterProvider<AppTopBarState> {
    override val values = sequenceOf(
        AppTopBarState(
            handlerBack = {},
            handlerUser = {}
        ),
        AppTopBarState(
            handlerUser = {}
        ),
        AppTopBarState(
            handlerBack = {}
        )
    )
}
