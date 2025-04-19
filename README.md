# nagara-wear-wear-os


## 概要

文字起こしから介護記録の情報を抽出して記録するサービスで用いるウェアラブルデバイスのコード。


## セットアップ

1. リポジトリのクローンと移動
```
git clone git@github.com:nagara-kaigo/nagara-wear-wear-os.git
cd nagara-wear-wear-os
```

2. `local.properties` の作成
```
touch local.properties
echo "OPENAI_API_KEY=[openai_api_key]" >> local.properties
echo "API_LOGIN_ID=[api_login_id]" >> local.properties
echo "API_PASSWORD=[api_password]" >> local.properties
echo "API_BASE_URL=[api_base_url]" >> local.properties
```

3. Android Studio で開いてビルド


## 構成
- `repository/` : APIやデータアクセス
- `viewmodel/` : Viewとデータの橋渡し
