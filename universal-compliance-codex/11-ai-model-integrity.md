# Universal Codex - Bölüm 11: AI Model Bütünlüğü ve İçerik Menşei
## 11.1 C2PA COMPLIANCE
- **Provenience:** Uygulama tarafından üretilen her türlü AI içeriği, C2PA standardında dijital imza ve menşei (provenance) bilgisi barındırmalıdır.
- **Verification:** `scripts/verify_model_hash.py` üzerinden, kullanılan AI modellerinin hash değerleri build zamanında fiziksel olarak tescil edilecektir.

## 11.2 DATA POISONING PROTECTION
Eğitim setleri ve model ağırlıkları (Weights), "Model-Integrity" mühürü ile kurumsal sızıntılara karşı fiziksel diskte izole edilmiştir.
