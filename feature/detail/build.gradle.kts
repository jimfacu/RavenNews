import ext.implementation

plugins {
    id(ModulePlugin.MODULE_NAME)
}

android {
    namespace = "com.raven.detail"

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    di()
    general()
    testing()
    network()
    navigation()

    implementation(project(":core"))
    implementation(project(":feature:home"))
}
