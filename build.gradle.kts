// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.jetbrains.kotlin.android) apply false
  alias(libs.plugins.diffplugSpotless)
}

spotless {
  kotlin {
    target("**/*.kt", "**/*.kts")
    targetExclude("${layout.buildDirectory}/**/*.kt", "bin/**/*.kt", "buildSrc/**/*.kt")

    ktlint()
  }
}
