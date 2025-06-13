package com.jesika0104.mybook.screen

import android.content.res.Configuration
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jesika0104.mybook.R
import com.jesika0104.mybook.ui.theme.MyBookTheme

@Composable
fun BukuDialog(
    bitmap: Bitmap?,
    onDismissRequest: () -> Unit,
    onConfirmation: (String, String) -> Unit
) {
    var judul by remember { mutableStateOf("") }
    var penulis by remember { mutableStateOf("") }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
           Column(
               modifier = Modifier.padding(16.dp),
               horizontalAlignment = Alignment.CenterHorizontally,
           ) {
               Image(
                   bitmap = bitmap!!.asImageBitmap(),
                   contentDescription = null,
                   modifier = Modifier.fillMaxWidth().aspectRatio(1f)
               )
               OutlinedTextField(
                   value = judul,
                   onValueChange = { judul = it },
                   label = { Text(text = stringResource(id = R.string.judul)) },
                   maxLines = 1,
                   keyboardOptions = KeyboardOptions(
                       capitalization = KeyboardCapitalization.Sentences,
                       imeAction = ImeAction.Done
                   ),
                   modifier = Modifier.padding(top = 8.dp)
               )
               OutlinedTextField(
                   value = penulis,
                   onValueChange = { penulis = it },
                   label = { Text(text = stringResource(id = R.string.penulis)) },
                   maxLines = 1,
                   keyboardOptions = KeyboardOptions(
                       capitalization = KeyboardCapitalization.Sentences,
                       imeAction = ImeAction.Done
                   ),
                   modifier = Modifier.padding(top = 8.dp)
               )
               Row(
                   modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                   horizontalArrangement = Arrangement.Center
               ) {
                   OutlinedButton(
                       onClick = { onDismissRequest() },
                       modifier = Modifier.padding(8.dp)
                   ) {
                       Text(text = stringResource(R.string.batal))
                   }
                   OutlinedButton(
                       onClick = { onConfirmation(judul, penulis) },
                       enabled = judul.isNotEmpty() && penulis.isNotEmpty(),
                       modifier = Modifier.padding(8.dp)
                   ) {
                       Text(text = stringResource(R.string.simpan))
                   }
               }
           }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun AddDialogPreview() {
    MyBookTheme {
        BukuDialog(
            bitmap = null,
            onDismissRequest = {},
            onConfirmation = {_, _ -> }
        )
    }
}