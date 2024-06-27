plugins {
  alias(libs.plugins.budgetapp.android.library)
  alias(libs.plugins.budgetapp.android.library.jacoco)
  alias(libs.plugins.budgetapp.android.hilt)
  alias(libs.plugins.budgetapp.android.room)
}

android {
  defaultConfig {
//    testInstrumentationRunner =
//      "com.ntg.core.database"
  }
  namespace = "com.ntg.core.database"
}

dependencies {
  api(project(":core:model"))

  implementation(libs.kotlinx.datetime)

}
