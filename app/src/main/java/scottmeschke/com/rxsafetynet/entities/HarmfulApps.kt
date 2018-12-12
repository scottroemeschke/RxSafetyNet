package scottmeschke.com.rxsafetynet.entities

import com.google.android.gms.safetynet.VerifyAppsConstants

data class HarmfulAppsInfo(val apps: List<HarmfulApp>,
                           val hoursSinceLastScanWithHarmfulApp: Int,
                           val lastScanTime: Long)

data class HarmfulApp(val harmCategory: HarmCategory,
                      val apkPackageName: String,
                      val apkSha256: String)

enum class HarmCategory {

  Unknown,
  Ransomware,
  Phishing,
  Trojan,
  Uncommon,
  Fraudware,
  TollFraud,
  WapFraud,
  CallFraud,
  Backdoor,
  Spyware,
  GenericMalware,
  HarmfulSite,
  WindowsMalware,
  HostileDownloader,
  NonAndroidThreat,
  Rooting,
  PrivilegeEscalation,
  Tracking,
  Spam,
  DenialOfService,
  DataCollection;

  companion object {
    fun fromCode(code: Int): HarmCategory {
      return when (code) {
        VerifyAppsConstants.HARMFUL_CATEGORY_BACKDOOR -> Backdoor
        VerifyAppsConstants.HARMFUL_CATEGORY_CALL_FRAUD -> CallFraud
        VerifyAppsConstants.HARMFUL_CATEGORY_DATA_COLLECTION -> DataCollection
        VerifyAppsConstants.HARMFUL_CATEGORY_DENIAL_OF_SERVICE -> DenialOfService
        VerifyAppsConstants.HARMFUL_CATEGORY_FRAUDWARE -> Fraudware
        VerifyAppsConstants.HARMFUL_CATEGORY_GENERIC_MALWARE -> GenericMalware
        VerifyAppsConstants.HARMFUL_CATEGORY_HARMFUL_SITE -> HarmfulSite
        VerifyAppsConstants.HARMFUL_CATEGORY_HOSTILE_DOWNLOADER -> HostileDownloader
        VerifyAppsConstants.HARMFUL_CATEGORY_NON_ANDROID_THREAT -> NonAndroidThreat
        VerifyAppsConstants.HARMFUL_CATEGORY_PHISHING -> Phishing
        VerifyAppsConstants.HARMFUL_CATEGORY_PRIVILEGE_ESCALATION -> PrivilegeEscalation
        VerifyAppsConstants.HARMFUL_CATEGORY_RANSOMWARE -> Ransomware
        VerifyAppsConstants.HARMFUL_CATEGORY_ROOTING -> Rooting
        VerifyAppsConstants.HARMFUL_CATEGORY_SPAM -> Spam
        VerifyAppsConstants.HARMFUL_CATEGORY_TOLL_FRAUD -> TollFraud
        VerifyAppsConstants.HARMFUL_CATEGORY_TRACKING -> Tracking
        VerifyAppsConstants.HARMFUL_CATEGORY_SPYWARE -> Spyware
        VerifyAppsConstants.HARMFUL_CATEGORY_TROJAN -> Trojan
        VerifyAppsConstants.HARMFUL_CATEGORY_UNCOMMON -> Uncommon
        VerifyAppsConstants.HARMFUL_CATEGORY_WAP_FRAUD -> WapFraud
        VerifyAppsConstants.HARMFUL_CATEGORY_WINDOWS_MALWARE -> WindowsMalware
        else -> Unknown
      }
    }
  }

}
