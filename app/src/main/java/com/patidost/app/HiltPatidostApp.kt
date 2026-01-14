package com.patidost.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * 🛡️ HAYALET RUHU BEDENE YERLEŞTİRME
 * Hilt'in bağımlılıkları enjekte edebilmesi için zorunlu olan temel Application sınıfı.
 * Bu, anayasal bütünlüğün yeniden sağlanması için son anatomik parçadır.
 */
@HiltAndroidApp
class HiltPatidostApp : Application()
