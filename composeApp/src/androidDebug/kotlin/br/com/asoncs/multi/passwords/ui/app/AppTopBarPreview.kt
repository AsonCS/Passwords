package br.com.asoncs.multi.passwords.ui.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.asoncs.multi.passwords.auth.User
import br.com.asoncs.multi.passwords.ui.PreviewContainer
import br.com.asoncs.multi.passwords.ui.PreviewPixel7

@PreviewPixel7
@Composable
private fun AppTopBarPreview() {
    PreviewContainer {
        AppTopBar(
            hasBackButton = true,
            modifier = Modifier,
            backHandler = {},
            user = User(
                name = "John Doe",
                photoUrl = null,
                email = "abc@com.br",
                uid = "uid"
            )
        )
    }
}
