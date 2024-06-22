// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.jetbrains.kotlin.android) apply false
  alias(libs.plugins.diffplugSpotless)
  alias(libs.plugins.jetbrains.kotlin.jvm) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.kotlinx.serialization) apply false
}

spotless {
  kotlin {
    target("**/*.kt", "**/*.kts")
    targetExclude("${layout.buildDirectory}/**/*.kt", "bin/**/*.kt", "buildSrc/**/*.kt")

    ktlint()
  }
}
