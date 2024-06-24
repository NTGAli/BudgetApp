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
  namespace = "com.ntg.budgetapp.core.database"
}

dependencies {
  api(project(":core:model"))
  api(project(":core:data"))

  implementation(libs.kotlinx.datetime)

}
