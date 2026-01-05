# Mathematical Foundation - Bölüm 12: Graf Sinir Ağları (GNN)
## 12.1 MESSAGE PASSING (MPNN)
- **Framework:** h_v^{(l+1)} = UPDATE(h_v^{(l)}, AGGREGATE({h_u^{(l)}, ∀u ∈ N(v)})).
- **Expressive Power:** GNN'lerin 1-WL testine (Weisfeiler-Lehman) eşdeğerliği mühürlenerek, graf yapısındaki ayırt edicilik tescil edilmiştir.

## 12.2 SPECTRAL CONVOLUTION (GCN)
Chebyshev polinomsal yaklaşımıyla O(K|E|) karmaşıklığı mühürlenmiş; spektral graf süzme (filtering) kapasitesi kurumsal sosyal ağ analizi için tescil edilmiştir.
