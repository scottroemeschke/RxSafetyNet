package scottmeschke.com.rxsafetynet

import io.reactivex.Completable
import io.reactivex.Single
import scottmeschke.com.rxsafetynet.entities.BrowsingThreatType
import scottmeschke.com.rxsafetynet.entities.HarmfulAppsInfo
import scottmeschke.com.rxsafetynet.entities.SafeBrowsingInfo

interface RxSafetyNet {

  fun attest(nonce: ByteArray): Single<String>

  fun promptEnableVerifyApps(): Single<Boolean>
  fun listHarmfulApps(): Single<HarmfulAppsInfo>
  fun isVerifyAppsEnabled(): Single<Boolean>

  fun initSafeBrowsing(): Completable
  fun shutdownSafeBrowsing(): Completable
  fun lookupUri(uri: String, vararg browsingThreatTypes: BrowsingThreatType): Single<SafeBrowsingInfo>

  fun verifyWithRecaptcha(siteKey: String): Single<String>

}
