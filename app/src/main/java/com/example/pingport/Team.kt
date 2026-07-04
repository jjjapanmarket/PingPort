package com.example.pingport

// チームのレベルを表す選択肢
enum class TeamLevel {
    PRO,
    SEMI_PRO,
    AMATEUR
}

// 募集の種類を表す選択肢
enum class RecruitType {
    PLAYER,
    COACH
}

// チーム1つ分の情報を持つ「箱の設計図」
data class Team(
    // チーム名
    val name: String,
    // 国
    val country: String,
    // 都市
    val city: String,
    // リーグ名
    val league: String,
    // チームのレベル
    val level: TeamLevel,
    // 選手募集かコーチ募集か
    val recruitType: RecruitType,
    // 求めるポジション（例：左利き歓迎、攻撃型）
    val wantedPosition: String,
    // 求める年齢・レベル感
    val wantedProfile: String,
    // 給料あり = true / なし = false
    val hasSalary: Boolean,
    // 住居提供あり = true / なし = false
    val hasHousing: Boolean,
    // 練習環境（週何回、施設など）
    val practiceInfo: String,
    // 連絡先（メール・SNS）
    val contact: String
)

// 開発用のダミーデータ（あとで本物のデータに置き換える）
// listOf(...) = 複数の値をまとめた「リスト」を作る関数
val dummyTeams = listOf(
    Team(
        name = "TTC Berlin Eagles",
        country = "ドイツ",
        city = "ベルリン",
        league = "ブンデスリーガ 3部",
        level = TeamLevel.SEMI_PRO,
        recruitType = RecruitType.PLAYER,
        wantedPosition = "左利き歓迎・攻撃型",
        wantedProfile = "20代前半・全国大会レベル",
        hasSalary = true,
        hasHousing = true,
        practiceInfo = "週5回・専用体育館あり",
        contact = "eagles@example.com"
    ),
    Team(
        name = "Barcelona TT Club",
        country = "スペイン",
        city = "バルセロナ",
        league = "地域リーグ 1部",
        level = TeamLevel.AMATEUR,
        recruitType = RecruitType.COACH,
        wantedPosition = "ジュニア指導経験があると尚可",
        wantedProfile = "年齢不問・指導に情熱がある方",
        hasSalary = true,
        hasHousing = false,
        practiceInfo = "週3回・市営体育館",
        contact = "@barcelona_tt（Instagram）"
    ),
    Team(
        name = "UTTC Salzburg",
        country = "オーストリア",
        city = "ザルツブルク",
        league = "卓球コーチ",
        level = TeamLevel.PRO,
        recruitType = RecruitType.PLAYER,
        wantedPosition = "両ハンドドライブ型",
        wantedProfile = "指導に情熱がある方",
        hasSalary = true,
        hasHousing = true,
        practiceInfo = "週6回・トレーナー帯同",
        contact = "recruit@parisping.example.com"
    )
)