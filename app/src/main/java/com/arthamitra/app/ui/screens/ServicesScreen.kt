package com.arthamitra.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class Scheme(
    val nameEn: String,
    val nameTe: String,
    val descEn: String,
    val descTe: String,
    val eligibilityEn: String,
    val eligibilityTe: String
)

val schemes = listOf(
    Scheme(
        nameEn = "PM Jan Dhan Yojana",
        nameTe = "పిఎం జన్ ధన్ యోజన",
        descEn = "Zero balance bank account with RuPay debit card and insurance cover",
        descTe = "రూపే డెబిట్ కార్డు మరియు బీమాతో జీరో బ్యాలెన్స్ బ్యాంక్ ఖాతా",
        eligibilityEn = "Any Indian citizen above 10 years of age",
        eligibilityTe = "10 సంవత్సరాలు పైబడిన భారత పౌరులు"
    ),
    Scheme(
        nameEn = "PM Mudra Yojana",
        nameTe = "పిఎం ముద్ర యోజన",
        descEn = "Loans up to ₹10 lakh for small businesses without collateral",
        descTe = "చిన్న వ్యాపారాలకు పూచీ లేకుండా ₹10 లక్షల వరకు రుణాలు",
        eligibilityEn = "Non-farm small/micro enterprises",
        eligibilityTe = "వ్యవసాయేతర చిన్న/సూక్ష్మ సంస్థలు"
    ),
    Scheme(
        nameEn = "Kisan Credit Card",
        nameTe = "కిసాన్ క్రెడిట్ కార్డ్",
        descEn = "Short-term credit for farmers at subsidised interest rates",
        descTe = "రైతులకు రాయితీ వడ్డీ రేట్లపై స్వల్పకాలిక రుణాలు",
        eligibilityEn = "Farmers, sharecroppers, tenant farmers",
        eligibilityTe = "రైతులు, వాటా రైతులు, కౌలు రైతులు"
    ),
    Scheme(
        nameEn = "PM-KISAN",
        nameTe = "పిఎం-కిసాన్",
        descEn = "₹6,000 per year direct income support to farmer families",
        descTe = "రైతు కుటుంబాలకు సంవత్సరానికి ₹6,000 నేరుగా ఖాతాలో",
        eligibilityEn = "Small and marginal farmers with cultivable land",
        eligibilityTe = "సాగుభూమి ఉన్న చిన్న మరియు సన్నకారు రైతులు"
    ),
    Scheme(
        nameEn = "Atal Pension Yojana",
        nameTe = "అటల్ పెన్షన్ యోజన",
        descEn = "Guaranteed pension of ₹1,000–5,000/month after age 60",
        descTe = "60 సంవత్సరాల తర్వాత నెలకు ₹1,000–5,000 హామీ పెన్షన్",
        eligibilityEn = "Indian citizens aged 18–40 with bank account",
        eligibilityTe = "బ్యాంక్ ఖాతా ఉన్న 18–40 వయసు భారత పౌరులు"
    )
)

@Composable
fun ServicesScreen(navController: NavController) {
    var isTeluguMode by remember { mutableStateOf(false) }

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

        Text(
            text = if (isTeluguMode) "ప్రభుత్వ పథకాలు" else "Government Schemes",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (isTeluguMode) "మీకు అర్హత ఉన్న పథకాలు చూడండి" else "Find schemes you may be eligible for",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(24.dp))

        schemes.forEach { scheme ->
            SchemeCard(scheme = scheme, isTeluguMode = isTeluguMode)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun SchemeCard(scheme: Scheme, isTeluguMode: Boolean) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = if (isTeluguMode) scheme.nameTe else scheme.nameEn,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (isTeluguMode) scheme.descTe else scheme.descEn,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (isTeluguMode) "అర్హత: ${scheme.eligibilityTe}" else "Eligibility: ${scheme.eligibilityEn}",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium
            )
        }
    }
}