package scottmeschke.com.rxsafetynet.internal.completable

import com.google.android.gms.safetynet.SafetyNetClient
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import scottmeschke.com.rxsafetynet.internal.listener.CompletableTaskRxTransformer

class InitSafeBrowsingCompletable(private val client: SafetyNetClient): Completable() {

  override fun subscribeActual(observer: CompletableObserver) {
    val listener = CompletableTaskRxTransformer(observer)
    observer.onSubscribe(listener)
    client.initSafeBrowsing().addOnCompleteListener(listener)
  }

}
