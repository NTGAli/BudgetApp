package com.ntg.core.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.ntg.samples.apps.budgetapp.core.designsystem.R

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    value: MutableState<String> = remember { mutableStateOf("") },
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
//    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    errorWithoutBorder: Boolean = false,
    errorText: String? = null,
    isOk: Boolean = false,
    okWithoutBorder: Boolean = false,
    okText: String? = null,
    isWarning: Boolean = false,
    warningWithoutBorder: Boolean = false,
    warningText: String? = null,
    showSupportingText: Boolean = false,
    isCountryShowing: Boolean = false,
    singleLine: Boolean = true,
    maxLine: Int = 1,
    minLine: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit = {}
) {

    val focusRequester = remember { FocusRequester() }

    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    enabled = true,
                    onClick = { onClick.invoke() },
                    indication = null,
                    interactionSource = interactionSource
                )
                .focusRequester(focusRequester),
            value = value.value,
            onValueChange = {
                value.value = it
            },
            enabled = enabled,
            readOnly = readOnly,
            textStyle = MaterialTheme.typography.labelLarge,
            label = {
                Text(
                    text = label.orEmpty(),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                    )
                )
            },
            placeholder = {
                Text(
                    text = placeholder.orEmpty(),
                    style = MaterialTheme.typography.labelSmall
                )
            },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon ?: {
                when {
                    isError || errorWithoutBorder -> {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_remove_circle),
                            contentDescription = "remove_circle",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }

                    isOk || okWithoutBorder -> {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_check_circle),
                            contentDescription = "check_circle",
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                    }

                    isWarning || warningWithoutBorder -> {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_warning_error),
                            contentDescription = "warning_circle",
                            tint = Color.Yellow
                        )
                    }
                }
            },
            prefix = prefix,
            suffix = suffix,
            supportingText = if (showSupportingText) {
                {
                    when {
                        isError && errorText.isNullOrEmpty() -> {
                            throw IllegalArgumentException("errorText is required when isError is true")
                        }

                        isOk && okText.isNullOrEmpty() -> {
                            throw IllegalArgumentException("okText is required when isOk is true")
                        }

                        isWarning && warningText.isNullOrEmpty() -> {
                            throw IllegalArgumentException("warningText is required when isWarning is true")
                        }

                        isError && !errorText.isNullOrEmpty() -> {
                            Text(
                                text = errorText,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.SemiBold,
                                )
                            )
                        }

                        isOk && !okText.isNullOrEmpty() -> {
                            Text(
                                text = okText,
                                color = MaterialTheme.colorScheme.tertiary,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.SemiBold,
                                )
                            )
                        }

                        isWarning && !warningText.isNullOrEmpty() -> {
                            Text(
                                text = warningText,
                                color = Color.Yellow,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.SemiBold,
                                )
                            )
                        }
                    }
                }
            } else null,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLine,
            minLines = minLine,
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                onClick.invoke()
                            }
                        }
                    }
                },
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (readOnly) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                disabledBorderColor = when {
                    isOk -> {
                        MaterialTheme.colorScheme.tertiary
                    }

                    isWarning -> {
                        Color.Yellow
                    }

                    isError -> {
                        MaterialTheme.colorScheme.error
                    }

                    readOnly -> {
                        MaterialTheme.colorScheme.secondary
                    }

                    else -> {
                        MaterialTheme.colorScheme.secondary
                    }
                },
                errorBorderColor = if (readOnly) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.error,
                focusedLabelColor = MaterialTheme.colorScheme.secondary,
                unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                disabledLabelColor = MaterialTheme.colorScheme.secondary,
                errorLabelColor = MaterialTheme.colorScheme.secondary,
                focusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
                disabledPlaceholderColor = MaterialTheme.colorScheme.secondary,
                errorPlaceholderColor = MaterialTheme.colorScheme.secondary,
                focusedTrailingIconColor = MaterialTheme.colorScheme.secondary,
                unfocusedTrailingIconColor = MaterialTheme.colorScheme.secondary,
                disabledTrailingIconColor = MaterialTheme.colorScheme.secondary,
                errorTrailingIconColor = MaterialTheme.colorScheme.secondary,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedTextColor = MaterialTheme.colorScheme.secondary,
                unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                disabledTextColor = when {
                    isCountryShowing -> {
                        MaterialTheme.colorScheme.secondary
                    }

                    readOnly -> {
                        MaterialTheme.colorScheme.secondary
                    }

                    else -> {
                        MaterialTheme.colorScheme.secondary
                    }
                },
                errorTextColor = MaterialTheme.colorScheme.secondary,
            )
        )
    }
}

/*
focusedTextColor = Color.Black,
unfocusedTextColor =Color.Black,
disabledTextColor =Color.Black,
errorTextColor =Color.Black,
focusedContainerColor =Color.Black,
unfocusedContainerColor =Color.Black,
disabledContainerColor =Color.Black,
errorContainerColor =Color.Black,
cursorColor =Color.Black,
errorCursorColor =Color.Black,
selectionColors =Color.Black,
focusedBorderColor =Color.Black,
unfocusedBorderColor =Color.Black,
disabledBorderColor =Color.Black,
errorBorderColor =Color.Black,
focusedLeadingIconColor =Color.Black,
unfocusedLeadingIconColor =Color.Black,
disabledLeadingIconColor =Color.Black,
errorLeadingIconColor =Color.Black,
focusedTrailingIconColor =Color.Black,
unfocusedTrailingIconColor =Color.Black,
disabledTrailingIconColor =Color.Black,
errorTrailingIconColor =Color.Black,
focusedLabelColor =Color.Black,
unfocusedLabelColor =Color.Black,
disabledLabelColor =Color.Black,
errorLabelColor =Color.Black,
focusedPlaceholderColor =Color.Black,
unfocusedPlaceholderColor =Color.Black,
disabledPlaceholderColor =Color.Black,
errorPlaceholderColor =Color.Black,
focusedSupportingTextColor =Color.Black,
unfocusedSupportingTextColor =Color.Black,
disabledSupportingTextColor =Color.Black,
errorSupportingTextColor =Color.Black,
focusedPrefixColor =Color.Black,
unfocusedPrefixColor =Color.Black,
disabledPrefixColor =Color.Black,
errorPrefixColor =Color.Black,
focusedSuffixColor =Color.Black,
unfocusedSuffixColor =Color.Black,
disabledSuffixColor =Color.Black,
errorSuffixColor =Color.Black,*/
