package com.patidost.app.domain.usecase

import com.patidost.app.core.util.Resource
import com.patidost.app.presentation.ui.util.UiText
import javax.inject.Inject

/**
 * ANAYASAL NOT: Bu UseCase, FAZ 3.2 restorasyonu sırasında geçersiz kılınmıştır.
 * Yeni anayasaya göre bu işlev, GivePatiUseCase ve givePati Cloud Function tarafından yürütülmektedir.
 * Bu dosya, build krizini çözmek için geçici olarak boşaltılmıştır ve daha sonra sistemden kaldırılacaktır.
 */
class GivePatiPointUseCase @Inject constructor() {
    suspend operator fun invoke(fromUserId: String, toPetId: String, amount: Int): Resource<Unit> {
        return Resource.Error(UiText.DynamicString("This function is deprecated."))
    }
}
