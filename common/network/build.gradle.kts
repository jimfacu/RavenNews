plugins {
    id(ModulePlugin.MODULE_NAME)
}

android {
    namespace = "com.raven.network"
}

dependencies {
    implementation(project(mapOf("path" to ":feature:home")))
    di()
    general()
    network()
}
