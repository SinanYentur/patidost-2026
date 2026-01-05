# Mathematical Foundation - Bölüm 11: Gerçek Zamanlı Akış İşleme
## 11.1 COUNT-MIN SKETCH (HEAVY HITTERS)
- **Error Bound:** estimate ≤ true_count + ε·N (prob 1-δ).
- **Space:** width = ceil(e/ε), depth = ceil(ln(1/δ)) mühürleri ile veri özetleme (sketching) kapasitesi tescil edilmiştir.

## 11.2 SLIDING WINDOW AGGREGATES
Exponential Histogram tekniği ile (1+ε)-approximation mühürü üzerinden, sonsuz veri akışında son kullanıcı metrikleri gerçek zamanlı taranacaktır.
