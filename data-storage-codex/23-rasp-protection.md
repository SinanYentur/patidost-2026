# Data & Storage Codex - Bölüm 23: RASP (Runtime Application Self-Protection)
## 23.1 DYNAMIC TAMPER DETECTION
- **Root/Jailbreak:** Uygulama açılışında ve her kritik işlemde (Auth, Payment) çok katmanlı root tespiti yapılmalıdır.
- **Debugger Check:** `Debug.isDebuggerConnected()` ve `TracerPid` mühürleriyle dinamik analiz girişimleri anında engellenmelidir.

## 23.2 INTEGRITY ENFORCEMENT
Uygulama imzası (Signature) ve install-source bütünlüğü, Play Integrity API üzerinden her 24 saatte bir fiziksel olarak tescil edilecektir.
