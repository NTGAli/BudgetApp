dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}
gradle.startParameter.excludedTaskNames.addAll(listOf(":convention"))

rootProject.name = "build-logic"
include(":convention")
