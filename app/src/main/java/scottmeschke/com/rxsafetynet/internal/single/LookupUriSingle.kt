package scottmeschke.com.rxsafetynet.internal.single

import com.google.android.gms.safetynet.SafetyNetApi
import com.google.android.gms.safetynet.SafetyNetClient
import io.reactivex.Single
import io.reactivex.SingleObserver
import scottmeschke.com.rxsafetynet.entities.BrowsingThreatType
import scottmeschke.com.rxsafetynet.entities.SafeBrowsingInfo
import scottmeschke.com.rxsafetynet.internal.listener.SingleTaskRxTransformer

class LookupUriSingle(private val uri: String, private val apiKey: String, private val browsingThreatTypes: Array<out BrowsingThreatType>, private val client: SafetyNetClient) : Single<SafeBrowsingInfo>() {

  override fun subscribeActual(observer: SingleObserver<in SafeBrowsingInfo>) {
    val listener = SingleTaskRxTransformer(observer, ::mapResponseToInfo)
    observer.onSubscribe(listener)
    val threatTypeCodes = browsingThreatTypes.map { BrowsingThreatType.toCode(it) }.toIntArray()
    client.lookupUri(uri, apiKey, *threatTypeCodes).addOnCompleteListener(listener)
  }

  private fun mapResponseToInfo(response: SafetyNetApi.SafeBrowsingResponse): SafeBrowsingInfo {
    val codes = response.detectedThreats.map {
      BrowsingThreatType.fromCode(it.threatType)
    }
    return SafeBrowsingInfo(codes)
  }

}
