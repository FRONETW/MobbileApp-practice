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

rootProject.name = "androidapp"
include(":app")
include(":w03")
include(":w03:w04")
include(":w05")
include(":w04")
include(":app_01")
include(":bubblegame")
include(":w06")
include(":snackgame")
include(":snackgame:snackgame")
include(":w06:appcompat")
include(":app:w09")
include(":w09")
include(":cupcake")
include(":w11")
