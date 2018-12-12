package scottmeschke.com.rxsafetynet.internal.single

import com.google.android.gms.safetynet.SafetyNetApi
import com.google.android.gms.safetynet.SafetyNetClient
import io.reactivex.Single
import io.reactivex.SingleObserver
import scottmeschke.com.rxsafetynet.entities.HarmCategory
import scottmeschke.com.rxsafetynet.entities.HarmfulApp
import scottmeschke.com.rxsafetynet.entities.HarmfulAppsInfo
import scottmeschke.com.rxsafetynet.internal.listener.SingleTaskRxTransformer

class ListHarmfulAppsSingle(private val client: SafetyNetClient): Single<HarmfulAppsInfo>() {

  override fun subscribeActual(observer: SingleObserver<in HarmfulAppsInfo>) {
    val listener = SingleTaskRxTransformer(observer, ::mapResponseToData)
    observer.onSubscribe(listener)
    client.listHarmfulApps().addOnCompleteListener(listener)
  }

  private fun mapResponseToData(response: SafetyNetApi.HarmfulAppsResponse): HarmfulAppsInfo {
    val apps = response.harmfulAppsList.map {
      HarmfulApp(
              HarmCategory.fromCode(it.apkCategory),
              it.apkPackageName,
              it.apkSha256?.contentToString() ?: ""
      )
    }
    return HarmfulAppsInfo(apps, response.hoursSinceLastScanWithHarmfulApp, response.lastScanTimeMs)
  }

}
