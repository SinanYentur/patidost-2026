#!/usr/bin/env bash
# validate-physical-layers.sh - V1500.60 Rule 13.2
echo "=== FİZİKSEL KATMAN VALİDASYONU ==="

# 1. Feature zinciri denetimi (UI -> Domain -> Data)
# ui/screen/{feature}/{subfeature} yapısını kontrol eder
for feature_dir in $(find app/src/main/java/com/patidost/app/ui/screen -maxdepth 2 -type d | tail -n +2); do
    f_name=$(basename $feature_dir)
    echo "🔍 Checking Feature: $f_name"
    
    # Requirement: Her feature'ın bir ViewModel'ı ve Screen dosyası olmalı
    [ -f "$feature_dir"/*Screen.kt ] && echo "  ✅ Screen found" || echo "  ❌ Screen MISSING"
    [ -f "$feature_dir"/*ViewModel.kt ] && echo "  ✅ ViewModel found" || echo "  ❌ ViewModel MISSING"
done

# 2. Web Kanıtı - Fiziksel Dosya Eşleşmesi
echo -e "\n🔍 Checking Evidence Integrity..."
[ -d "project-evidence" ] && echo "✅ Evidence folder exists" || echo "❌ Evidence folder MISSING"
