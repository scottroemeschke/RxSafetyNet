package scottmeschke.com.rxsafetynet.internal.single

import com.google.android.gms.safetynet.SafetyNetApi
import com.google.android.gms.safetynet.SafetyNetClient
import io.reactivex.Single
import io.reactivex.SingleObserver
import scottmeschke.com.rxsafetynet.internal.listener.SingleTaskRxTransformer

class VerifyWithRecaptchaSingle(private val siteKey: String, private val client: SafetyNetClient) : Single<String>() {

  override fun subscribeActual(observer: SingleObserver<in String>) {
    val listener = SingleTaskRxTransformer(observer, SafetyNetApi.RecaptchaTokenResponse::getTokenResult)
    observer.onSubscribe(listener)
    client.verifyWithRecaptcha(siteKey).addOnCompleteListener(listener)
  }

}
