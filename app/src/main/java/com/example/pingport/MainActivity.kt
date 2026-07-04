package com.example.pingport

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pingport.ui.theme.PingPortTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PingPortTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // チーム一覧画面を表示する
                    TeamListScreen(
                        teams = dummyTeams,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// チーム一覧画面（画面全体の部品）
@Composable
fun TeamListScreen(teams: List<Team>, modifier: Modifier = Modifier) {
    // LazyColumn = スクロールできる縦並びリスト
    // 「画面に見えている分だけ描画する」ので、データが増えても軽い
    LazyColumn(modifier = modifier) {
        // items = リストの中身を1件ずつ取り出して表示する
        items(teams) { team ->
            TeamCard(team = team)
        }
    }
}

// チーム1件分のカード（一覧の1行の部品）
@Composable
fun TeamCard(team: Team, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp) // カードの外側の
    ) {
        // Column = 縦に並べるレイアウト
        Column(modifier = Modifier.padding(16.dp)) {  // カードの内側の余白
            Text(
                text = team.name,
                style = MaterialTheme.typography.titleMedium // タイトル用
            )
            Text(text = "${team.country}・${team.city}")
            Text(text = "${levelLabel(team.level)} / ${recruitLabel(team.recruitType)}")
        }
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
        TeamListScreen(teams = dummyTeams)
    }
}