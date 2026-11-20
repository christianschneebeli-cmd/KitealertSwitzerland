package ch.christianschneebeli.kitealert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Air
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Ink = Color(0xFF17364B)
private val Blue = Color(0xFF176F96)
private val Muted = Color(0xFF526A78)
private val Canvas = Color(0xFFF2F6F6)
private val PaleBlue = Color(0xFFE9F4F8)
private val Green = Color(0xFF2E7158)
private val PaleGreen = Color(0xFFEAF5EF)
private val Amber = Color(0xFFA76513)
private val PaleAmber = Color(0xFFFFF3DC)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WindcheckerTheme {
                WindcheckerHome()
            }
        }
    }
}

@Composable
private fun WindcheckerTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Blue,
            onPrimary = Color.White,
            secondary = Green,
            background = Canvas,
            surface = Color.White,
            onSurface = Ink,
            onSurfaceVariant = Muted
        ),
        content = content
    )
}

@Composable
private fun WindcheckerHome() {
    Scaffold(
        containerColor = Canvas,
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = { Icon(Icons.Outlined.Home, contentDescription = null) },
                    label = { Text("Uebersicht") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Outlined.Place, contentDescription = null) },
                    label = { Text("Spots") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Outlined.Settings, contentDescription = null) },
                    label = { Text("Einstellungen") }
                )
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "SPOTGUIDE / SCHWEIZ",
                    color = Blue,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                )
                PreviewTag()
            }
            Spacer(Modifier.height(14.dp))
            Text(
                text = "Wind im Blick.",
                color = Ink,
                fontSize = 32.sp,
                lineHeight = 36.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = "Deine Spots und Windfenster auf einen Blick.",
                color = Muted,
                fontSize = 15.sp
            )
            Spacer(Modifier.height(22.dp))
            DemoForecastCard()
            Spacer(Modifier.height(12.dp))
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(vertical = 14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Blue)
            ) {
                Icon(Icons.Outlined.Refresh, contentDescription = null)
                Spacer(Modifier.width(10.dp))
                Text("Jetzt pruefen", fontWeight = FontWeight.SemiBold)
            }
            Spacer(Modifier.height(28.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Text("DEINE PROFILE", color = Muted, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                    Spacer(Modifier.height(4.dp))
                    Text("4 eingerichtet", color = Ink, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                Text("DEMO", color = Blue, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(12.dp))
            ProfileList()
            Spacer(Modifier.height(16.dp))
            AutomationNote()
            Spacer(Modifier.height(20.dp))
        }
    }
}

@Composable
private fun PreviewTag() {
    Surface(color = PaleAmber, shape = RoundedCornerShape(6.dp)) {
        Text(
            text = "VORSCHAU",
            modifier = Modifier.padding(horizontal = 9.dp, vertical = 6.dp),
            color = Amber,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.6.sp
        )
    }
}

@Composable
private fun DemoForecastCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Ink,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(Modifier.padding(18.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.Air, contentDescription = null, tint = Color(0xFF9CD8E5), modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Text("ZUERICHSEE", color = Color(0xFFB7CFD8), fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                Spacer(Modifier.weight(1f))
                Text("BEISPIEL", color = Color(0xFFB7CFD8), fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(16.dp))
            Text("11-16 kt", color = Color.White, fontSize = 34.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(3.dp))
            Text("Nordost  /  Heute, 14:00", color = Color(0xFFD5E3E8), fontSize = 14.sp)
            Spacer(Modifier.height(16.dp))
            Surface(color = Color(0xFF25485B), shape = RoundedCornerShape(6.dp)) {
                Text(
                    "Keine Live-Daten - nur ein UI-Mockup",
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp),
                    color = Color.White,
                    fontSize = 11.sp
                )
            }
        }
    }
}

private data class ProfilePreview(
    val name: String,
    val spotCount: String,
    val threshold: String,
    val accent: Color,
    val tint: Color
)

@Composable
private fun ProfileList() {
    val profiles = listOf(
        ProfilePreview("Zuerich", "4 Spots", "ab 11 kt", Blue, PaleBlue),
        ProfilePreview("Bodensee", "2 Spots", "ab 6 kt", Green, PaleGreen),
        ProfilePreview("Italien", "5 Spots", "ab 11 kt", Amber, PaleAmber),
        ProfilePreview("Snowkite", "3 Spots", "ab 7 kt", Blue, PaleBlue)
    )

    Surface(color = Color.White, shape = RoundedCornerShape(10.dp)) {
        Column {
            profiles.forEachIndexed { index, profile ->
                ProfileRow(profile)
                if (index < profiles.lastIndex) {
                    HorizontalDivider(color = Canvas, modifier = Modifier.padding(horizontal = 14.dp))
                }
            }
        }
    }
}

@Composable
private fun ProfileRow(profile: ProfilePreview) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(color = profile.tint, shape = CircleShape, modifier = Modifier.size(38.dp)) {
            androidx.compose.foundation.layout.Box(contentAlignment = Alignment.Center) {
                Icon(Icons.Outlined.Place, contentDescription = null, tint = profile.accent, modifier = Modifier.size(19.dp))
            }
        }
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(profile.name, color = Ink, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(2.dp))
            Text(profile.spotCount, color = Muted, fontSize = 12.sp)
        }
        Text(profile.threshold, color = profile.accent, fontSize = 13.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.width(4.dp))
        Icon(Icons.Outlined.ChevronRight, contentDescription = null, tint = Muted, modifier = Modifier.size(20.dp))
    }
}

@Composable
private fun AutomationNote() {
    Surface(color = PaleBlue, shape = RoundedCornerShape(10.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(color = Color.White, shape = CircleShape, modifier = Modifier.size(36.dp)) {
                androidx.compose.foundation.layout.Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.Outlined.Refresh, contentDescription = null, tint = Blue, modifier = Modifier.size(18.dp))
                }
            }
            Spacer(Modifier.width(12.dp))
            Column {
                Text("Automatik", color = Ink, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(2.dp))
                Text("Wird in einem spaeteren Schritt eingerichtet", color = Muted, fontSize = 12.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WindcheckerHomePreview() {
    WindcheckerTheme {
        WindcheckerHome()
    }
}