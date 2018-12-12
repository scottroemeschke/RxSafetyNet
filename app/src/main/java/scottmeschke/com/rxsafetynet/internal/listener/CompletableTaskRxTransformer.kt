package scottmeschke.com.rxsafetynet.internal.listener

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable

class CompletableTaskRxTransformer(private val observer: CompletableObserver): OnCompleteListener<Void>, Disposable {

  @Volatile private var disposed = false

  override fun onComplete(completedTask: Task<Void>) {
    if (completedTask.isSuccessful) observer.onComplete()
    else observer.onError(completedTask.exception!!)
  }

  override fun isDisposed() = disposed

  override fun dispose() {
    disposed = true
  }

}
