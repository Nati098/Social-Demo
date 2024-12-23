package ru.social.demo.pages.profile

import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.Timestamp
import ru.social.demo.MainContract
import ru.social.demo.MainViewModel
import ru.social.demo.R
import ru.social.demo.data.model.Post
import ru.social.demo.data.model.User
import ru.social.demo.pages.post_editor.components.AttachmentMedia
import ru.social.demo.services.FirestoreClient
import ru.social.demo.services.FsPath
import ru.social.demo.ui.components.CBottomSheet
import ru.social.demo.ui.components.buttons.CIconButton
import ru.social.demo.ui.components.buttons.CTextButton
import ru.social.demo.ui.components.text.CTextField
import ru.social.demo.ui.theme.SDTheme
import ru.social.demo.utils.ImageUtils
import ru.social.demo.utils.NetworkUtils
import ru.social.demo.utils.toBase64

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEditorSheet(
    user: User,
    isBottomSheetVisible: Boolean,
    sheetState: SheetState,
    onDismissRequest: () -> Unit = {}
) {
    val context = LocalContext.current
    val mainViewModel: MainViewModel = viewModel(context as ComponentActivity)
    val userViewState by mainViewModel.userViewState.observeAsState()

    val name = remember { mutableStateOf(user.name ?: "") }
    val imageUrl = remember { mutableStateOf(user.imageUrl ?: "") }
    val birth = remember { mutableStateOf(user.birthday ?: "") }
//    val gender = remember { mutableStateOf(user.gender) }
//    val favGenres = remember { mutableStateOf(user.genres) }

    CBottomSheet(
        isBottomSheetVisible = isBottomSheetVisible,
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        onBack = onDismissRequest,
        actions = {
            CTextButton(
                label = stringResource(R.string.post_edit),
                enabled = name.value.isNotEmpty(),
                onClick = { }
            )
        }
    ) {

        Column {
            CTextField(
                value = name.value,
                textStyle = SDTheme.typography.headingS,
                singleLine = true,
                onValueChange = { name.value = it }
            )
            CTextField(
                modifier = Modifier.weight(1f),
                value = imageUrl.value,
                onValueChange = { imageUrl.value = it }
            )

        }

    }

}
