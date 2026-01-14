# 0001_CORE_GENESIS.md

## Patidost’un Doğum Belgesi

Bu kayıt, projenin başlangıç mimarisini ve temel felsefesini mühürler.

### FAZ 1 Başlangıcı
Proje, **Core Infrastructure** fazı ile doğmuştur. Amaç, sağlam, ölçeklenebilir ve test edilebilir bir temel atmaktır.

### Mimari Kabuller
- **Clean Architecture**: Domain, Data ve UI katmanları kesin bir şekilde ayrılmıştır. Bu, iş mantığının platformdan ve detaylardan bağımsız kalmasını sağlar.
- **Hilt (Dependency Injection)**: Bağımlılık yönetimi için standart olarak Hilt seçilmiştir. Bu, modülerliği ve test edilebilirliği artırır.
- **Room (Offline-first)**: Veri kalıcılığı için Room kütüphanesi ve offline-first yaklaşımı benimsenmiştir. Bu, uygulamanın ağ bağlantısı olmadan da çalışabilmesini garanti eder.
- **Gradle Version Catalog**: Bağımlılık sürümleri merkezileştirilmiştir. Bu, tutarlılığı sağlar ve güncellemeleri kolaylaştırır.
