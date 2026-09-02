plugins {
    id("java")
    id("application")
}

repositories {
    mavenCentral()
}

val lwjglVersion = "3.3.6"


//fetches correct natives for the current platform (windows, macos, linux)
val lwjglNatives = when {
    org.gradle.nativeplatform.platform.internal.DefaultNativePlatform.getCurrentOperatingSystem().isWindows -> "natives-windows"
    org.gradle.nativeplatform.platform.internal.DefaultNativePlatform.getCurrentOperatingSystem().isMacOsX -> "natives-macos"
    org.gradle.nativeplatform.platform.internal.DefaultNativePlatform.getCurrentOperatingSystem().isLinux -> "natives-linux"
    else -> "natives-linux"
}

dependencies {
    implementation(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))

    implementation("org.lwjgl:lwjgl")
    implementation("org.lwjgl:lwjgl-glfw")
    implementation("org.lwjgl:lwjgl-opengl")

    runtimeOnly("org.lwjgl:lwjgl::$lwjglNatives")
    runtimeOnly("org.lwjgl:lwjgl-glfw::$lwjglNatives")
    runtimeOnly("org.lwjgl:lwjgl-opengl::$lwjglNatives")
}

application {
    mainClass.set("org.example.Main")
}

