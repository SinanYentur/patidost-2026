# SBOM & Privacy Codex - Bölüm 3: SBOM Doğrulama ve Uyum
## 3.1 COMPREHENSIVE VALIDATION
- **Schema:** CycloneDX ve SPDX şemalarına %100 fiziksel uyum zorunludur.
- **Signatures:** PGP ve JWS imzalarıyla SBOM bütünlüğü her build'de tescil edilecektir.

## 3.2 POLICY ENFORCEMENT
`AllowedLicenses` ve `BannedLicenses` (GPL-3.0, AGPL-3.0) listeleri üzerinden otomatik uyum mühürlemesi yapılacaktır.
