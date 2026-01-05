# Phase 3: SBOM (Software Bill of Materials) Stratejisi
## 8.1 GENERATION SYSTEM
- **CycloneDX 1.4:** Bağımlılıklar, lisanslar ve hash bilgileri JSON formatında mühürlenecektir.
- **SPDX 2.3:** Kurumsal raporlama ve yasal denetim için yedek SBOM formatı zorunludur.

## 8.2 VERIFICATION & COMPLIANCE
- `scripts/verify_sbom.sh` üzerinden her build öncesi bağımlılık bütünlüğü ve CVSS skoru taranacaktır.
- **Fail Strategy:** Kritik veya yüksek seviyeli zafiyet tespit edilen her build anayasal olarak bloklanacaktır.
