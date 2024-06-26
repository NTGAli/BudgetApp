pluginManagement {
  repositories {
    includeBuild("build-logic")
    google()
    mavenCentral()
    gradlePluginPortal()
  }
}
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
  }
}


rootProject.name = "budgetApp"
include(":app")
include(":core:model")
include(":core:designsystem")
include(":core:data")
include(":feature:category")
include(":core:database")
include(":feature:transaction")
include(":core:common")
include(":core:analytics")
include(":feature:home")
include(":feature:setup")
