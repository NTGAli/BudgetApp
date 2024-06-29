plugins {
  alias(libs.plugins.budgetapp.android.feature)
  alias(libs.plugins.budgetapp.android.library.compose)
  alias(libs.plugins.budgetapp.android.library.jacoco)
  alias(libs.plugins.roborazzi)
}

android {
  namespace = "com.ntg.budgetapp.feature.transaction"
}

dependencies {
  implementation(libs.accompanist.permissions)
  implementation(project(":core:data"))
  implementation(project(":core:database"))
  implementation(project(":core:common"))
  implementation(project(":core:designsystem"))
  implementation(project(":feature:category"))

  testImplementation(libs.robolectric)
}
