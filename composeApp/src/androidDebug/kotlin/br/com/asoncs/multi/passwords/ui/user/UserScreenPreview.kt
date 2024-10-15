package br.com.asoncs.multi.passwords.ui.user

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.asoncs.multi.passwords.auth.AuthMock.mockUser
import br.com.asoncs.multi.passwords.ui.PreviewContainer
import br.com.asoncs.multi.passwords.ui.PreviewPixel7

@PreviewPixel7
@Composable
private fun UserScreenPreview() {
    PreviewContainer {
        UserScreen(
            modifier = Modifier,
            onLogout = {},
            user = mockUser
        )
    }
}
