package com.careerfair.companion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ── Colors ────────────────────────────────────────────────────────────────────
private val BgColor       = Color(0xFFEAEEF8)
private val CardColor     = Color.White
private val PrimaryBtn    = Color(0xFF4B3FE4)
private val TextPrimary   = Color(0xFF0F172A)
private val TextSecondary = Color(0xFF64748B)
private val BorderColor   = Color(0xFFE2E8F0)

// ── MainActivity ──────────────────────────────────────────────────────────────
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                HomeScreen()
            }
        }
    }
}

// ── HomeScreen ────────────────────────────────────────────────────────────────
@Composable
fun HomeScreen(
    onScanClick:     () -> Unit = {},
    onContactsClick: () -> Unit = {},
    onExportClick:   () -> Unit = {},
    onFollowUpClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgColor)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(64.dp))

        // ── Title ──
        Text(
            text = "FairPath",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryBtn
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Your career fair companion",
            fontSize = 14.sp,
            color = TextSecondary
        )

        Spacer(Modifier.height(32.dp))

        // ── Primary Actions Card ──
        SectionCard {
            Text(
                text = "Primary Actions",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = onScanClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBtn)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = "Scan Badge",
                        tint = Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "Scan Badge",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // ── Secondary Actions Card ──
        SectionCard {
            Text(
                text = "Secondary Actions",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SecondaryActionButton(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.Group,
                    label = "Contacts",
                    onClick = onContactsClick
                )
                SecondaryActionButton(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.Share,
                    label = "Export",
                    onClick = onExportClick
                )
                SecondaryActionButton(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.Email,
                    label = "Follow-up",
                    onClick = onFollowUpClick
                )
            }
        }
    }
}

// ── Reusable: Section Card ────────────────────────────────────────────────────
@Composable
private fun SectionCard(content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            content = content
        )
    }
}

// ── Reusable: Secondary Action Button ────────────────────────────────────────
@Composable
private fun SecondaryActionButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .border(1.dp, BorderColor, RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = TextPrimary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = TextPrimary
        )
    }
}

// ── Preview ───────────────────────────────────────────────────────────────────
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen()
    }
}