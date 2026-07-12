package com.example.pingport

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.BackHandler
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pingport.ui.theme.PingPortTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PingPortTheme {
                PingPortApp()
            }
        }
    }
}

//アプリ全体の親。「今どこの画面か」の状態を待ち、一覧と詳細に切り替える
@Composable
fun PingPortApp(modifier: Modifier = Modifier) {
    //選択中のチーム。null＝何も選んでない＝一覧画面
    var selectedTeam by remember { mutableStateOf<Team?>(null) }
    val team = selectedTeam
    if (team == null) {
        //一覧画面
        TeamListScreen(
            teams = dummyTeams,
            onTeamClick = { tapped -> selectedTeam = tapped },
            modifier = modifier
        )
    } else {
        //詳細画面
        BackHandler {
            selectedTeam = null
        }
        TeamDetailScreen(team = team, onBack = { selectedTeam = null }, modifier = modifier)
    }
}
// チーム一覧画面（画面全体の部品）
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamListScreen(teams: List<Team>, onTeamClick: (Team) -> Unit, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        // topBar = 画面上部の帯。タイトルを出す
        topBar = {
            TopAppBar(
                title = { Text("PingPort") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        // LazyColumn = スクロールできる縦並びリスト
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            // items = リストの中身を1件ずつ取り出して表示する
            items(teams) { team ->
                TeamCard(
                    team = team,
                    onClick = { onTeamClick(team) }
                )
            }
        }
    }
}

// チーム1件分のカード（一覧の1行の部品）
@Composable
fun TeamCard(team: Team, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp) // カードの外側の
    ) {
        // Column = 縦に並べるレイアウト
        Column(modifier = Modifier.padding(16.dp)) {  // カードの内側の余白
            // 1行目: チーム名（左）とバッジ（右）を横並びに
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = team.name,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f) // 余った横幅を全部もらう
                )
                RecruitBadge(type = team.recruitType)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "${countryFlag(team.country)} ${team.country}・${team.city}")
            Text(
                text = levelLabel(team.level),
                color = MaterialTheme.colorScheme.onSurfaceVariant // 控えめな灰色
            )
        }
    }
}

// 募集タイプを示す色付きバッジ（一目で選手募集かコーチ募集か分かるように）
@Composable
fun RecruitBadge(type: RecruitType, modifier: Modifier = Modifier) {
    // 募集タイプごとに「地の色」と「文字色」のペアを選ぶ
    val backgroundColor = when (type) {
        RecruitType.PLAYER -> MaterialTheme.colorScheme.primaryContainer
        RecruitType.COACH -> MaterialTheme.colorScheme.tertiaryContainer
    }
    val textColor = when (type) {
        RecruitType.PLAYER -> MaterialTheme.colorScheme.onPrimaryContainer
        RecruitType.COACH -> MaterialTheme.colorScheme.onTertiaryContainer
    }
    Text(
        text = recruitLabel(type),
        style = MaterialTheme.typography.labelMedium,
        color = textColor,
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}

// 国名を国旗の絵文字に変換する
fun countryFlag(country: String): String {
    return when (country) {
        "ドイツ" -> "🇩🇪"
        "スペイン" -> "🇪🇸"
        "オーストリア" -> "🇦🇹"
        else -> ""
    }
}

// enumを画面表示用の日本語に変換する関数
fun levelLabel(level: TeamLevel): String {
    // when = 「どの選択肢か」で分岐する書き方（Javaのswitchの強化版）
    return when (level) {
        TeamLevel.PRO -> "プロ"
        TeamLevel.SEMI_PRO -> "セミプロ"
        TeamLevel.AMATEUR -> "アマチュア"
    }
}

fun recruitLabel(type: RecruitType): String {
    return when (type) {
        RecruitType.PLAYER -> "選手募集"
        RecruitType.COACH -> "コーチ募集"
    }
}

// Android Studio上で見た目を確認するためのプレビュー
@Preview(showBackground = true)
@Composable
fun TeamListPreview() {
    PingPortTheme {
        TeamListScreen(teams = dummyTeams, onTeamClick = {})
    }
}