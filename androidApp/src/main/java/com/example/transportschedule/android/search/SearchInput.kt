package com.example.transportschedule.android.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.example.transportschedule.models.PlainStation
import kotlin.math.min

@Composable
fun SearchInput(
    title: String,
    suggestions: List<PlainStation>,
    didSelectStation: (PlainStation)->Unit
) {
    var expanded = remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }
    val icon = if (expanded.value)
        Icons.Filled.ArrowDropUp
    else
        Icons.Filled.ArrowDropDown
    var dropDownWidth by remember { mutableStateOf(0) }
    Column {
        OutlinedTextField(
            value = selectedText,
            onValueChange = {
                expanded.value = true
                selectedText = it
            },
            modifier = Modifier.fillMaxWidth()
                .onSizeChanged {
                    dropDownWidth = it.width
                },
            label = {Text(title)},
            trailingIcon = {
                Icon(icon,"contentDescription", Modifier.clickable {
                    expanded.value = !(expanded.value)
                })
            },
            maxLines = 1,
            singleLine = true
        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier
                .width(with(LocalDensity.current){dropDownWidth.toDp()})
                .heightIn(min = 100.dp, max = 450.dp),
            properties = PopupProperties(focusable = false, dismissOnClickOutside = true)
        ) {
            val filteredSuggestions = suggestions.filter { it.title.lowercase().startsWith(selectedText.lowercase()) }
            filteredSuggestions.subList(0, min(10, filteredSuggestions.count())).forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label.title
                    expanded.value = false
                    didSelectStation(label)
                },
                enabled = true) {
                    Text(text = label.title)
                }
            }
        }
    }
}