package com.example.fairpath.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fairpath.FairPathApplication
import com.example.fairpath.R
import com.example.fairpath.data.Contact
import com.example.fairpath.navigation.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ManualEntryScreen(navController: NavController) {
    val context = LocalContext.current
    val repository = (context.applicationContext as FairPathApplication).contactRepository
    val scope = rememberCoroutineScope()

    var name by remember { mutableStateOf("") }
    var company by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var customTag by remember { mutableStateOf("") }
    val appliedTags = remember { mutableStateListOf<String>() }

    val suggestedTags = listOf(
        stringResource(R.string.tag_high_priority),
        stringResource(R.string.tag_follow_up_soon),
        stringResource(R.string.tag_interesting)
    )

    val fieldColors = OutlinedTextFieldDefaults.colors(
        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.review_contact)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    FormField(
                        label = stringResource(R.string.field_name),
                        value = name,
                        onValueChange = { name = it },
                        placeholder = stringResource(R.string.hint_full_name),
                        colors = fieldColors
                    )
                    FormField(
                        label = stringResource(R.string.field_company),
                        value = company,
                        onValueChange = { company = it },
                        placeholder = stringResource(R.string.hint_company_name),
                        colors = fieldColors
                    )
                    FormField(
                        label = stringResource(R.string.field_role),
                        value = role,
                        onValueChange = { role = it },
                        placeholder = stringResource(R.string.hint_job_title),
                        colors = fieldColors
                    )
                    FormField(
                        label = stringResource(R.string.field_email),
                        value = email,
                        onValueChange = { email = it },
                        placeholder = stringResource(R.string.hint_email),
                        colors = fieldColors
                    )
                    FormField(
                        label = stringResource(R.string.field_phone),
                        value = phone,
                        onValueChange = { phone = it },
                        placeholder = stringResource(R.string.hint_phone),
                        colors = fieldColors
                    )
                    FormField(
                        label = stringResource(R.string.field_quick_note),
                        value = note,
                        onValueChange = { note = it },
                        placeholder = stringResource(R.string.hint_quick_note),
                        colors = fieldColors,
                        minLines = 3
                    )

                    // Tags section
                    Text(
                        text = stringResource(R.string.field_tags),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = stringResource(R.string.tags_suggested),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        suggestedTags.filter { it !in appliedTags }.forEach { tag ->
                            SuggestionChip(
                                onClick = { appliedTags.add(tag) },
                                label = { Text("+ $tag") }
                            )
                        }
                    }

                    if (appliedTags.isNotEmpty()) {
                        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            appliedTags.toList().forEach { tag ->
                                InputChip(
                                    selected = true,
                                    onClick = { appliedTags.remove(tag) },
                                    label = { Text(tag) },
                                    trailingIcon = {
                                        Icon(
                                            Icons.Default.Add,
                                            contentDescription = null
                                        )
                                    }
                                )
                            }
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = customTag,
                            onValueChange = { customTag = it },
                            modifier = Modifier.weight(1f),
                            placeholder = { Text(stringResource(R.string.hint_custom_tag)) },
                            singleLine = true,
                            colors = fieldColors
                        )
                        IconButton(
                            onClick = {
                                val tag = customTag.trim()
                                if (tag.isNotEmpty() && tag !in appliedTags) {
                                    appliedTags.add(tag)
                                    customTag = ""
                                }
                            }
                        ) {
                            Icon(Icons.Default.Add, contentDescription = null)
                        }
                    }

                    Text(
                        text = stringResource(R.string.tags_helper),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val newContact = Contact(
                        name = name.trim(),
                        company = company.trim(),
                        role = role.trim(),
                        email = email.trim().ifBlank { null },
                        phone = phone.trim(),
                        note = note.trim(),
                        tags = appliedTags.toList()
                    )
                    scope.launch {
                        repository.add(newContact)
                        navController.navigate(Screen.ContactCard.createRoute(newContact.id)) {
                            popUpTo(Screen.ManualEntry.route) { inclusive = true }
                        }
                    }
                },
                enabled = name.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.button_save_contact))
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    colors: androidx.compose.material3.TextFieldColors,
    minLines: Int = 1
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(placeholder) },
            singleLine = minLines == 1,
            minLines = minLines,
            colors = colors
        )
    }
}
