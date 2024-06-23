plugins {
  alias(libs.plugins.budgetapp.android.library)
  alias(libs.plugins.budgetapp.android.library.compose)
  alias(libs.plugins.budgetapp.android.library.jacoco)
  alias(libs.plugins.roborazzi)
}

android {
  defaultConfig {
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  namespace = "com.ntg.samples.apps.budgetapp.core.designsystem"
}

dependencies {

  api(libs.androidx.compose.foundation)
  api(libs.androidx.compose.foundation.layout)
  api(libs.androidx.compose.material.iconsExtended)
  api(libs.androidx.compose.material3)
  api(libs.androidx.compose.runtime)
  api(libs.androidx.compose.ui.util)

  implementation(libs.coil.kt.compose)

  testImplementation(libs.androidx.compose.ui.test)
  testImplementation(libs.hilt.android.testing)
  testImplementation(libs.robolectric)
  testImplementation(libs.roborazzi)

  androidTestImplementation(libs.androidx.compose.ui.test)
}
