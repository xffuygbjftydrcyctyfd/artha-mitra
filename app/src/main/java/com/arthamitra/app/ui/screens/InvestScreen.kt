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
fun InvestScreen(navController: NavController) {
    var isTeluguMode by remember { mutableStateOf(false) }
    var monthlyInvestment by remember { mutableStateOf("") }
    var annualReturn by remember { mutableStateOf("") }
    var years by remember { mutableStateOf("") }
    var corpusResult by remember { mutableStateOf("") }
    var totalInvested by remember { mutableStateOf("") }
    var totalGains by remember { mutableStateOf("") }

    val title = if (isTeluguMode) "SIP లెక్కలు" else "SIP Calculator"
    val monthlyLabel = if (isTeluguMode) "నెలసరి పెట్టుబడి (₹)" else "Monthly Investment (₹)"
    val returnLabel = if (isTeluguMode) "వార్షిక రాబడి (%)" else "Expected Annual Return (%)"
    val yearsLabel = if (isTeluguMode) "సంవత్సరాలు" else "Investment Period (years)"
    val calculateLabel = if (isTeluguMode) "లెక్కించు" else "Calculate"
    val corpusLabel = if (isTeluguMode) "మొత్తం నిధి" else "Total Corpus"
    val investedLabel = if (isTeluguMode) "మొత్తం పెట్టుబడి" else "Total Invested"
    val gainsLabel = if (isTeluguMode) "మొత్తం లాభం" else "Total Gains"

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

        OutlinedTextField(
            value = monthlyInvestment,
            onValueChange = { monthlyInvestment = it },
            label = { Text(monthlyLabel) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = annualReturn,
            onValueChange = { annualReturn = it },
            label = { Text(returnLabel) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = years,
            onValueChange = { years = it },
            label = { Text(yearsLabel) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val p = monthlyInvestment.toDoubleOrNull()
                val r = annualReturn.toDoubleOrNull()?.div(12 * 100)
                val n = years.toDoubleOrNull()?.times(12)
                if (p != null && r != null && n != null && r > 0) {
                    val corpus = p * ((1 + r).pow(n) - 1) / r * (1 + r)
                    val invested = p * n
                    val gains = corpus - invested
                    corpusResult = "₹%.2f".format(corpus)
                    totalInvested = "₹%.2f".format(invested)
                    totalGains = "₹%.2f".format(gains)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(calculateLabel, fontSize = 16.sp)
        }

        if (corpusResult.isNotEmpty()) {
            Spacer(modifier = Modifier.height(24.dp))
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(20.dp)) {
                    InvestResultRow(label = corpusLabel, value = corpusResult)
                    Spacer(modifier = Modifier.height(8.dp))
                    InvestResultRow(label = investedLabel, value = totalInvested)
                    Spacer(modifier = Modifier.height(8.dp))
                    InvestResultRow(label = gainsLabel, value = totalGains)
                }
            }
        }
    }
}

@Composable
fun InvestResultRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, fontWeight = FontWeight.Medium)
        Text(value, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
    }
}