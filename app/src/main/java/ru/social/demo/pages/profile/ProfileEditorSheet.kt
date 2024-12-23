package ru.social.demo.pages.profile

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.social.demo.MainViewModel
import ru.social.demo.R
import ru.social.demo.data.model.User
import ru.social.demo.services.FirestoreClient
import ru.social.demo.services.FsPath
import ru.social.demo.ui.components.CBottomSheet
import ru.social.demo.ui.components.buttons.CTextButton
import ru.social.demo.ui.components.text.CTextField
import ru.social.demo.ui.components.text.DataTextField
import ru.social.demo.ui.components.text.MaskTransformation
import ru.social.demo.ui.components.text.RoundedTextField
import ru.social.demo.ui.theme.SDTheme
import ru.social.demo.utils.NetworkUtils
import ru.social.demo.utils.birthdayInputToTimestamp
import ru.social.demo.utils.parseBirthdayDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEditorSheet(
    user: User,
    isBottomSheetVisible: Boolean,
    sheetState: SheetState,
    onDismissRequest: () -> Unit = {}
) {
//    val context = LocalContext.current
//    val mainViewModel: MainViewModel = viewModel(context as ComponentActivity)
//    val userViewState by mainViewModel.userViewState.observeAsState()

    val name = remember { mutableStateOf("") }
    val imageUrl = remember { mutableStateOf("") }
    val birth = remember { mutableStateOf("") }
//    val gender = remember { mutableStateOf(user.gender) }
//    val favGenres = remember { mutableStateOf(user.genres) }

    name.value = user.name ?: ""
    imageUrl.value = user.imageUrl ?: ""
    birth.value = user.birthday?.parseBirthdayDate() ?: ""

    CBottomSheet(
        isBottomSheetVisible = isBottomSheetVisible,
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        onBack = onDismissRequest,
        actions = {
            CTextButton(
                label = stringResource(R.string.post_edit),
                enabled = name.value.isNotEmpty(),
                onClick = {
                    onDismissRequest()
                    NetworkUtils.makeCallIO {
                        FirestoreClient.getInstance().updateData(
                            FsPath.USERS,
                            user.copy(name = name.value, imageUrl = imageUrl.value, birthday = birth.value.birthdayInputToTimestamp())
                        )
                    }
                }
            )
        }
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            RoundedTextField(
                value = name.value,
                hint = stringResource(R.string.name),
                onValueChange = { name.value = it }
            )
            RoundedTextField(
                value = imageUrl.value,
                hint = stringResource(R.string.image_url),
                onValueChange = { imageUrl.value = it }
            )
            DataTextField(
                value = birth.value,
                onValueChange = { birth.value = it }
            )
        }

    }

}
