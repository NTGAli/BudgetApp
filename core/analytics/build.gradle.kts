plugins {
  alias(libs.plugins.budgetapp.android.library)
  alias(libs.plugins.budgetapp.android.library.compose)
  alias(libs.plugins.budgetapp.android.hilt)
}

android {
  namespace = "com.ntg.budgetapp.core.analytics"

  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.14"
  }
}

dependencies {
  implementation(libs.androidx.compose.runtime)

  prodImplementation(platform(libs.firebase.bom))
  prodImplementation(libs.firebase.analytics)
}
