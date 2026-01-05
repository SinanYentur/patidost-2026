# Mathematical Foundation - Bölüm 1: Kriptografik Protokoller
## 1.1 ELLIPTIC CURVE CRYPTOGRAPHY (ECC)
- **Equation:** y² = x³ + ax + b (mod p)
- **Standard:** secp256r1 (NIST P-256)
- **Operation:** ECDH shared secret derivation via Scalar Multiplication (k * P).
- **Security:** Asal sayı teorisi ve ayrık logaritma problemi (DLP) üzerine mühürlenmiştir.

## 1.2 DIFFIE-HELLMAN (DH) PROOF
- **Protocol:** g^(ab) mod p = (g^a)^b mod p.
- **Verification:** Alice ve Bob'un ortak gizli anahtarı, üçüncü taraflarca bilinemeyen s_alice = s_bob eşitliği ile fiziksel olarak kanıtlanmıştır.
