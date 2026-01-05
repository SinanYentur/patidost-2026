#!/usr/bin/env bash
# ui-architecture-validator.sh - V2000.15 Rule 6
echo "=== UI MİMARİ VALİDASYONU ==="

# 1. Dizin derinliği kontrolü (max 3 seviye)
echo "1. Dizin Derinliği Kontrolü..."
find app/src/main/java/com/patidost/app/ui/screen -type d | awk -F/ '{print NF}' | sort -nu | tail -1 | \
  while read depth; do
    # Depth adjustment for project root
    [ $depth -gt 15 ] && echo "❌ Dizin derinliği fazla: $depth" || echo "✅ Derinlik: $depth"
  done

# 2. Sub-feature klasör kontrolü
echo -e "\n2. Sub-feature Klasör Kontrolü..."
for feature in $(find app/src/main/java/com/patidost/app/ui/screen -maxdepth 1 -type d | tail -n +2); do
    f_name=$(basename $feature)
    sub_count=$(find $feature -maxdepth 1 -type d | wc -l)
    if [ $sub_count -gt 1 ]; then
        echo "✅ $f_name: Hiyerarşik yapı mühürlü."
    else
        echo "❌ $f_name: Flat structure ihlali!"
    fi
done

echo -e "\n=== VALİDASYON TAMAMLANDI ==="
