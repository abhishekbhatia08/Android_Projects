import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun UnitConverterScreen() {
    var inputValue by remember { mutableStateOf(value = "") }
    var outputValue by remember { mutableStateOf(value = "") }
    var inputUnit by remember { mutableStateOf(value = "Meters") }
    var outputUnit by remember { mutableStateOf(value = "Meters") }
    var inputExpanded by remember { mutableStateOf(value = false) }
    var outputExpanded by remember { mutableStateOf(value = false) }
    var inputConversionFactor by remember { mutableDoubleStateOf(value = 1.0) }
    var outputConversionFactor by remember { mutableDoubleStateOf(value = 1.0) }

    val options = mapOf(
        "Meters" to 1.0,
        "Kilometers" to 0.001,
        "Centimeters" to 100.0,
        "Feet" to 3.28084
    )

    fun convertUnit() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * outputConversionFactor) / inputConversionFactor
        outputValue = String.format("%.2f", result)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Unit Converter",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = inputValue,
            onValueChange = { newValue ->
                inputValue = newValue
                outputValue = ""
            },
            label = { Text(text = "Enter Value") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            DropdownButton(
                label = "Input Unit",
                options = options,
                expanded = inputExpanded,
                selectedOption = inputUnit,
                onOptionSelected = { selectedOption, _ ->
                    inputUnit = selectedOption
                    inputConversionFactor = options[selectedOption]!!
                    convertUnit()
                },
                onExpandedChanged = { isExpanded ->
                    inputExpanded = isExpanded
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            DropdownButton(
                label = "Output Unit",
                options = options,
                expanded = outputExpanded,
                selectedOption = outputUnit,
                onOptionSelected = { selectedOption, _ ->
                    outputUnit = selectedOption
                    outputConversionFactor = options[selectedOption]!!
                    convertUnit()
                },
                onExpandedChanged = { isExpanded ->
                    outputExpanded = isExpanded
                },
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (outputValue.isNotEmpty())
            Text(
                text = "Input Value is $inputValue $inputUnit\nOutput Value is $outputValue $outputUnit",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )

    }
}

@Composable
fun DropdownButton(
    label: String,
    options: Map<String, Double>,
    expanded: Boolean,
    selectedOption: String,
    onOptionSelected: (String, Double) -> Unit,
    onExpandedChanged: (Boolean) -> Unit
) {
    // Dropdown Button
    Box() {
        Button(
            onClick = {
                onExpandedChanged(!expanded);
            },
            modifier = Modifier.wrapContentSize(),
        ) {
            Text(text = if (selectedOption.isBlank()) label else selectedOption)
            Icon(
                imageVector = if (expanded) Icons.Default.ArrowDropDown else Icons.Default.ArrowDropDown,
                contentDescription = null,
            )

        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChanged(false) }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option.key) },
                    onClick = {
                        onOptionSelected(option.key, option.value)
                        onExpandedChanged(false)
                    }
                )
            }
        }
    }
}