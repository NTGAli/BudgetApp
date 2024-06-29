plugins {
  alias(libs.plugins.budgetapp.android.feature)
  alias(libs.plugins.budgetapp.android.library.compose)
  alias(libs.plugins.budgetapp.android.library.jacoco)
  alias(libs.plugins.roborazzi)
}

android {
  namespace = "com.ntg.feature.category"
}

dependencies {
  implementation(project(":core:designsystem"))
  implementation(project(":core:data"))
  implementation(project(":core:database"))
  implementation(project(":core:model"))
  implementation(project(":core:common"))
}
