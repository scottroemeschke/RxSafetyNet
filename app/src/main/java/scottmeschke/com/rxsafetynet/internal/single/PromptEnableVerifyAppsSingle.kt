package scottmeschke.com.rxsafetynet.internal.single

import com.google.android.gms.safetynet.SafetyNetApi
import com.google.android.gms.safetynet.SafetyNetClient
import io.reactivex.Single
import io.reactivex.SingleObserver
import scottmeschke.com.rxsafetynet.internal.listener.SingleTaskRxTransformer

class PromptEnableVerifyAppsSingle(private val client: SafetyNetClient): Single<Boolean>() {

  override fun subscribeActual(observer: SingleObserver<in Boolean>) {
    val listener = SingleTaskRxTransformer(observer, SafetyNetApi.VerifyAppsUserResponse::isVerifyAppsEnabled)
    observer.onSubscribe(listener)
    client.enableVerifyApps()
            .addOnCompleteListener(listener)
  }

}
