package scottmeschke.com.rxsafetynet.entities

import com.google.android.gms.safetynet.SafeBrowsingThreat

enum class BrowsingThreatType {

  Unknown,
  PotentiallyHarmfulApplication,
  SocialEngineering;

  companion object {

    fun fromCode(code: Int): BrowsingThreatType {
      return when (code) {
        SafeBrowsingThreat.TYPE_POTENTIALLY_HARMFUL_APPLICATION -> PotentiallyHarmfulApplication
        SafeBrowsingThreat.TYPE_SOCIAL_ENGINEERING -> SocialEngineering
        else -> Unknown
      }
    }

    fun toCode(type: BrowsingThreatType): Int {
      return when(type) {
        PotentiallyHarmfulApplication -> SafeBrowsingThreat.TYPE_POTENTIALLY_HARMFUL_APPLICATION
        SocialEngineering -> SafeBrowsingThreat.TYPE_SOCIAL_ENGINEERING
        else -> 0
      }
    }
  }
}

data class SafeBrowsingInfo(val threats: List<BrowsingThreatType>) {
  fun hasThreats() = threats.isNotEmpty()
}