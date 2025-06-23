pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "AnimeApp"
include(":app")
include(":core")
include(":common")
include(":featureHome")
include(":featureDetail")
include(":featureFavorite")

project(":featureHome").projectDir = file("features/featureHome")
project(":featureDetail").projectDir = file("features/featureDetail")
project(":featureFavorite").projectDir = file("features/featureFavorite")
