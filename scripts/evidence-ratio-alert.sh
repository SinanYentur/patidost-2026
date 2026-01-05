#!/usr/bin/env bash
# evidence-ratio-alert.sh - V1500.60 Rule 13.5
echo "=== KANIT / KOD ORANI DENETİMİ ==="

EVIDENCE_COUNT=$(find project-evidence -type f | wc -l)
CODE_COUNT=$(find app/src/main/java -name "*.kt" | wc -l)

# Ratio Calculation (Avoid division by zero)
if [ "$CODE_COUNT" -eq 0 ]; then
    RATIO=100
else
    RATIO=$(( (EVIDENCE_COUNT * 100) / CODE_COUNT ))
fi

echo "📊 Kanıt Dosyası: $EVIDENCE_COUNT"
echo "📊 Kod Dosyası: $CODE_COUNT"
echo "📈 Oran: $RATIO%"

if [ "$RATIO" -lt 80 ]; then
    echo "🚨 KRİTİK: Kanıt toplama yetersiz! (Hedef %80)"
    # exit 1 # Bloklama gerekirse aktif edilir
else
    echo "✅ PASS: Kanıt bütünlüğü anayasal sınırlar içinde."
fi
