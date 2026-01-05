# Universal Codex - Bölüm 10: Sürdürülebilir Hesaplama
## 10.1 CARBON-AWARE SCHEDULING
- **Energy Monitor:** CPU, GPU ve Network tüketimi `EnergyMonitor` mühürü üzerinden anlık izlenecektir.
- **Optimal Window:** Arkaplan görevleri, enerji şebekesinin karbon yoğunluğunun en düşük olduğu zaman dilimlerinde (`CarbonAwareScheduler`) mühürlenecektir.

## 10.2 MEDIA EFFICIENCY
Batarya seviyesi %20 altına düştüğünde, medya akışları (Streaming) otomatik olarak AV1 kodeği ve 480p kalite mühürlerine çekilecektir.
