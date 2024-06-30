plugins {
  alias(libs.plugins.budgetapp.android.library)
  alias(libs.plugins.budgetapp.android.library.jacoco)
  alias(libs.plugins.budgetapp.android.hilt)
  id("kotlinx-serialization")
}

android {
  namespace = "com.ntg.core.data"
  testOptions {
    unitTests {
      isIncludeAndroidResources = true
      isReturnDefaultValues = true
    }
  }
}

dependencies {
  implementation(project(":core:database"))
  implementation(project(":core:analytics"))
  implementation(project(":core:common"))
  testImplementation(libs.kotlinx.coroutines.test)
  testImplementation(libs.kotlinx.serialization.json)
}
