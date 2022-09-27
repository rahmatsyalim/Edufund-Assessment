import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
   alias(libs.plugins.android.application) apply false
   alias(libs.plugins.android.library) apply false
   alias(libs.plugins.kotlin.android) apply false
   alias(libs.plugins.hilt) apply false
   alias(libs.plugins.ben.manes.versions)
}

fun String.isStable(): Boolean {
   val stableKeyword = listOf("RELEASE", "FINAL", "GA").any {
      toUpperCase(java.util.Locale.ROOT).contains(it)
   }
   val regex = "^[0-9,.v-]+(-r)?$".toRegex()
   return stableKeyword || regex.matches(this)
}

tasks.withType<DependencyUpdatesTask>() {
   rejectVersionIf {
      !candidate.version.isStable() && currentVersion.isStable()
   }
}