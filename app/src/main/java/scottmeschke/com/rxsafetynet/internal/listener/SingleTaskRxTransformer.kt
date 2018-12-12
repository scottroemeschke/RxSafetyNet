package scottmeschke.com.rxsafetynet.internal.listener

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class SingleTaskRxTransformer<OriginalType, OutputType>(private val observer: SingleObserver<in OutputType>, private val transformFunction: (OriginalType) -> OutputType)
  : Disposable, OnCompleteListener<OriginalType> {

  @Volatile private var disposed = false

  override fun isDisposed() = disposed

  override fun dispose() {
    disposed = true
  }

  override fun onComplete(completedTask: Task<OriginalType>) {
    if (!disposed) emitResult(completedTask)
  }

  private fun emitResult(completedTask: Task<OriginalType>) {
    if (completedTask.isSuccessful) {
      observer.onSuccess(transformFunction(completedTask.result!!))
    }
    else {
      observer.onError(completedTask.exception!!)
    }
  }

}
