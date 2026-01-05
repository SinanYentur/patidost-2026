#!/bin/bash
# 🛡️ Rule 300: Enterprise Secrets Management
# Bu betik, GitHub Secrets yapısını mühürlemek için rehberlik eder.

echo "🚨 DO NOT COMMIT KEYSTORE.PROPERTIES!"
echo "1. GitHub Repository > Settings > Secrets and Variables > Actions kısmına git."
echo "2. Aşağıdaki secret'ları ekle:"
echo "   - STORE_FILE_BASE64 (Keystore dosyasının base64 hali)"
echo "   - STORE_PASSWORD"
echo "   - KEY_ALIAS"
echo "   - KEY_PASSWORD"
echo "   - GCP_SERVICE_ACCOUNT_JSON"
echo "3. CI workflow bu verileri çalışma zamanında çözüp kullanacak."
