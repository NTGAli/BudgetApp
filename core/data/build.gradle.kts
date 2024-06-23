plugins {
  alias(libs.plugins.budgetapp.android.library)
  alias(libs.plugins.budgetapp.android.library.jacoco)
  alias(libs.plugins.budgetapp.android.hilt)
  id("kotlinx-serialization")
}

android {
  namespace = "com.ntg.samples.apps.budgetapp.core.data"
  testOptions {
    unitTests {
      isIncludeAndroidResources = true
      isReturnDefaultValues = true
    }
  }
}

dependencies {
  testImplementation(libs.kotlinx.coroutines.test)
  testImplementation(libs.kotlinx.serialization.json)
}
