plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "restaurant"
include("restaurant-api")
include("restaurant-app")
include("restaurant-client")
include("restaurant-client-starter")
include("restaurant-data")
include("restaurant-data-api")
