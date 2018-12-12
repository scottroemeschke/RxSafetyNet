package scottmeschke.com.rxsafetynet

import android.content.Context
import scottmeschke.com.rxsafetynet.internal.RxSafetyNetImpl

object RxSafetyNetFactory {
  fun create(context: Context, apiKey: String): RxSafetyNet {
    return RxSafetyNetImpl(context, apiKey)
  }
}
