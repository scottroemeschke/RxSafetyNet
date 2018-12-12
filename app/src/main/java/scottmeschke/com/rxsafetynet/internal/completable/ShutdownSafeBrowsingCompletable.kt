package scottmeschke.com.rxsafetynet.internal.completable

import com.google.android.gms.safetynet.SafetyNetClient
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import scottmeschke.com.rxsafetynet.internal.listener.CompletableTaskRxTransformer

class ShutdownSafeBrowsingCompletable(private val client: SafetyNetClient): Completable() {

  override fun subscribeActual(observer: CompletableObserver) {
    val listener = CompletableTaskRxTransformer(observer)
    observer.onSubscribe(listener)
    client.shutdownSafeBrowsing().addOnCompleteListener(listener)
  }

}
