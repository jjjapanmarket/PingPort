package com.example.pingport

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pingport.ui.theme.PingPortTheme

// チーム詳細画面（1チームの全情報を表示する）
@Composable
fun TeamDetailScreen(team: Team, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // 画面に収まらないときスクロールできるようにする
            .padding(16.dp)
    ) {
        // チーム名（大きめの見出し）
        Text(
            text = team.name,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "${team.country}・${team.city}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp)) // 縦方向の空白
        HorizontalDivider() // 区切り線
        Spacer(modifier = Modifier.height(16.dp))

        // 各項目を「ラベル + 内容」のセットで表示
        DetailItem(label = "リーグ", value = team.league)
        DetailItem(label = "レベル", value = levelLabel(team.level))
        DetailItem(label = "募集", value = recruitLabel(team.recruitType))
        DetailItem(label = "求めるポジション", value = team.wantedPosition)
        DetailItem(label = "求める人物像", value = team.wantedProfile)
        DetailItem(label = "給料", value = if (team.hasSalary) "あり" else "なし")
        DetailItem(label = "住居提供", value = if (team.hasHousing) "あり" else "なし")
        DetailItem(label = "練習環境", value = team.practiceInfo)
        DetailItem(label = "連絡先", value = team.contact)
    }
}

// 「ラベル + 内容」1セット分の小さな部品
@Composable
fun DetailItem(label: String, value: String) {
    Column(modifier = Modifier.padding(bottom = 12.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium // 小さめのラベル文字
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

// プレビュー（ダミーデータの1件目を表示して確認）
@Preview(showBackground = true)
@Composable
fun TeamDetailPreview() {
    PingPortTheme {
        TeamDetailScreen(team = dummyTeams[0])
    }
}