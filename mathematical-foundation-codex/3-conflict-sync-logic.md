# Mathematical Foundation - Bölüm 3: Çakışma Çözümü ve Küme Teorisi
## 3.1 VECTOR CLOCKS (CAUSALITY)
- **Operation:** İki event arasındaki Happens-before (←) ve Concurrent (||) ilişkileri Vector Clock karşılaştırmasıyla mühürlenmiştir.
- **Merge:** Component-wise MAX operasyonu ile nedensellik mühürleri senkronize edilecektir.

## 3.2 LATTICE THEORY (CRDT)
- **Properties:** Join (∨) semilattice aksiyomları (Commutative, Associative, Idempotent) tescil edilmiştir.
- **Proof:** Monotonik join semilattice yapısı, sistemin Eventual Consistency (Nihai Yakınsama) garantisidir.
