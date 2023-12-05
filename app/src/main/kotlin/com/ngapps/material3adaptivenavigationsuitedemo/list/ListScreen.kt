package com.ngapps.material3adaptivenavigationsuitedemo.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ngapps.material3adaptivenavigationsuitedemo.R
import com.ngapps.material3adaptivenavigationsuitedemo.ui.theme.MainTheme

@Composable
internal fun ListRoute(
    modifier: Modifier = Modifier,
) {

    ListScreen(
        modifier = modifier,
    )
}

@Composable
internal fun ListScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = stringResource(id = R.string.list))
    }
}

@Preview
@Composable
fun ListScreenPreview() {
    MainTheme {
        ListScreen()
    }
}
