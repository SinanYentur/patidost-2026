# Compose 2026 Codex - Bölüm 7: Derived State ve Snapshot Flow
## 7.1 DERIVED STATE CACHING
- **Expensive Computations:** Hesaplama maliyeti yüksek her UI işlemi `derivedStateOf` ile sarmalanarak recomposition yükü fiziksel olarak düşürülmelidir.
- **Snapshot Flow:** Compose state'lerini `snapshotFlow` ile reaktif akışlara dönüştürerek analitik ve log mühürleri basılacaktır.

## 7.2 DEBOUNCING
State değişimlerine tepki verirken `debounce(300)` mühürüyle gereksiz işlemleri engellemek anayasal zorunluluktur.
