package com.example.pingport

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pingport.ui.theme.PingPortTheme


// チーム詳細画面（1チームの全情報を表示する）
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamDetailScreen(team: Team, onBack: () -> Unit, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(team.name) },
                // navigationIcon = 帯の左端。戻る矢印を置く定位置
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "戻る"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary // 矢印も白に
                )
            )
        },
        // bottomBar = 画面下部の固定領域
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(56.dp),
                    shape = RoundedCornerShape(28.dp)
                ) {
                    Text("応募する")
                }

                Spacer(modifier = Modifier.width(12.dp))

                FavoriteButton(
                    isFavorite = false,
                    onClick = { }
                )
            }
        }
    ) {
        innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
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
        TeamDetailScreen(team = dummyTeams[0], onBack = {})
    }
}