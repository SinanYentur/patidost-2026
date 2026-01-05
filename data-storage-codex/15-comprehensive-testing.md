# Data & Storage Codex - Bölüm 15: Kapsamlı Test Stratejisi
## 15.1 TESTING PYRAMID
- **Unit Tests:** %70+ coverage hedefiyle iş mantığı mühürlenmelidir.
- **Integration Tests:** Veritabanı migrasyonları ve ağ kontratları fiziksel olarak test edilmelidir.
- **Chaos Engineering:** Ağ kopması ve veri bozulması senaryoları simüle edilmelidir (Rule 15.1).

## 15.2 PERFORMANCE BENCHMARKS
Startup zamanı ve jank oranları `BenchmarkRule` üzerinden her sürümde fiziksel olarak tescillenmelidir.
