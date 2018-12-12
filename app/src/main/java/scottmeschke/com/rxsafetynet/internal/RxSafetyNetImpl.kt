package scottmeschke.com.rxsafetynet.internal

import android.content.Context
import com.google.android.gms.safetynet.SafetyNet
import io.reactivex.Completable
import io.reactivex.Single
import scottmeschke.com.rxsafetynet.entities.BrowsingThreatType
import scottmeschke.com.rxsafetynet.entities.HarmfulAppsInfo
import scottmeschke.com.rxsafetynet.RxSafetyNet
import scottmeschke.com.rxsafetynet.internal.completable.InitSafeBrowsingCompletable
import scottmeschke.com.rxsafetynet.internal.completable.ShutdownSafeBrowsingCompletable
import scottmeschke.com.rxsafetynet.internal.single.*

class RxSafetyNetImpl(context: Context, private val apiKey: String) : RxSafetyNet {

  private val client = SafetyNet.getClient(context)

  override fun attest(nonce: ByteArray) = AttestSingle(nonce, apiKey, client)

  override fun promptEnableVerifyApps(): Single<Boolean>  = PromptEnableVerifyAppsSingle(client)

  override fun listHarmfulApps(): Single<HarmfulAppsInfo> = ListHarmfulAppsSingle(client)

  override fun isVerifyAppsEnabled(): Single<Boolean> = IsVerifyAppsEnabledSingle(client)

  override fun initSafeBrowsing(): Completable  = InitSafeBrowsingCompletable(client)

  override fun shutdownSafeBrowsing(): Completable = ShutdownSafeBrowsingCompletable(client)

  override fun lookupUri(uri: String, vararg browsingThreatTypes: BrowsingThreatType) = LookupUriSingle(uri, apiKey, browsingThreatTypes, client)

  override fun verifyWithRecaptcha(siteKey: String): Single<String> = VerifyWithRecaptchaSingle(siteKey, client)

}
