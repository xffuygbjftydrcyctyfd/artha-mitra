package com.arthamitra.app.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    var isTeluguMode by remember { mutableStateOf(false) }

    val title = if (isTeluguMode) "అర్థ మిత్ర" else "Artha Mitra"
    val subtitle = if (isTeluguMode) "మీ ఆర్థిక సహాయకుడు" else "Your Financial Friend"
    val loanLabel = if (isTeluguMode) "రుణాలు" else "Loans"
    val investLabel = if (isTeluguMode) "పెట్టుబడి" else "Invest"
    val servicesLabel = if (isTeluguMode) "సేవలు" else "Services"
    val toggleLabel = if (isTeluguMode) "English" else "తెలుగు"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = title,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = subtitle,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { isTeluguMode = !isTeluguMode },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text(toggleLabel)
        }

        Spacer(modifier = Modifier.height(48.dp))

        HomeCard(
            title = loanLabel,
            description = if (isTeluguMode) "EMI లెక్కలు, రుణ అర్హత" else "EMI calculator, loan eligibility",
            onClick = { navController.navigate("loans") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        HomeCard(
            title = investLabel,
            description = if (isTeluguMode) "SIP, FD, RD లెక్కలు" else "SIP, FD, RD calculators",
            onClick = { navController.navigate("invest") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        HomeCard(
            title = servicesLabel,
            description = if (isTeluguMode) "ప్రభుత్వ పథకాలు, ఆర్థిక అక్షరాస్యత" else "Govt schemes, financial literacy",
            onClick = { navController.navigate("services") }
        )
    }
}

@Composable
fun HomeCard(title: String, description: String, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = description, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}