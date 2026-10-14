package com.arthamitra.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.math.pow

@Composable
fun LoanScreen(navController: NavController) {
    var isTeluguMode by remember { mutableStateOf(false) }
    var principal by remember { mutableStateOf("") }
    var interestRate by remember { mutableStateOf("") }
    var tenureMonths by remember { mutableStateOf("") }
    var emiResult by remember { mutableStateOf("") }
    var totalInterest by remember { mutableStateOf("") }
    var totalAmount by remember { mutableStateOf("") }

    val title = if (isTeluguMode) "రుణ లెక్కలు" else "Loan Calculator"
    val principalLabel = if (isTeluguMode) "అసలు మొత్తం (₹)" else "Principal Amount (₹)"
    val rateLabel = if (isTeluguMode) "వడ్డీ రేటు (% సంవత్సరానికి)" else "Interest Rate (% per year)"
    val tenureLabel = if (isTeluguMode) "కాలం (నెలలు)" else "Tenure (months)"
    val calculateLabel = if (isTeluguMode) "లెక్కించు" else "Calculate"
    val emiLabel = if (isTeluguMode) "నెలసరి EMI" else "Monthly EMI"
    val totalInterestLabel = if (isTeluguMode) "మొత్తం వడ్డీ" else "Total Interest"
    val totalLabel = if (isTeluguMode) "మొత్తం చెల్లింపు" else "Total Payment"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = { navController.popBackStack() }) {
                Text(if (isTeluguMode) "వెనక్కి" else "Back")
            }
            Button(
                onClick = { isTeluguMode = !isTeluguMode },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(if (isTeluguMode) "English" else "తెలుగు")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(title, fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = principal,
                onValueChange = { principal = it },
                label = { Text(principalLabel) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            VoiceMicButton(isTeluguMode = isTeluguMode) { result ->
                principal = result.filter { it.isDigit() }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = interestRate,
                onValueChange = { interestRate = it },
                label = { Text(rateLabel) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.weight(1f)
            )
            VoiceMicButton(isTeluguMode = isTeluguMode) { result ->
                interestRate = result.filter { it.isDigit() || it == '.' }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = tenureMonths,
                onValueChange = { tenureMonths = it },
                label = { Text(tenureLabel) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            VoiceMicButton(isTeluguMode = isTeluguMode) { result ->
                tenureMonths = result.filter { it.isDigit() }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val p = principal.toDoubleOrNull()
                val r = interestRate.toDoubleOrNull()?.div(12 * 100)
                val n = tenureMonths.toDoubleOrNull()
                if (p != null && r != null && n != null && r > 0) {
                    val emi = p * r * (1 + r).pow(n) / ((1 + r).pow(n) - 1)
                    val total = emi * n
                    val interest = total - p
                    emiResult = "₹%.2f".format(emi)
                    totalInterest = "₹%.2f".format(interest)
                    totalAmount = "₹%.2f".format(total)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(calculateLabel, fontSize = 16.sp)
        }

        if (emiResult.isNotEmpty()) {
            Spacer(modifier = Modifier.height(24.dp))
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(20.dp)) {
                    ResultRow(label = emiLabel, value = emiResult)
                    Spacer(modifier = Modifier.height(8.dp))
                    ResultRow(label = totalInterestLabel, value = totalInterest)
                    Spacer(modifier = Modifier.height(8.dp))
                    ResultRow(label = totalLabel, value = totalAmount)
                }
            }
        }
    }
}

@Composable
fun ResultRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, fontWeight = FontWeight.Medium)
        Text(value, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
    }
}