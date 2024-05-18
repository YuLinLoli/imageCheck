import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.8.0"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.noarg") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion
    id("net.mamoe.mirai-console") version "2.16.0"
    id("com.github.gmazzo.buildconfig") version "4.1.1"

}
mirai {
    noTestCore = true
    setupConsoleTestRuntime {
        // 移除 mirai-core 依赖
        classpath = classpath.filter {
            !it.nameWithoutExtension.startsWith("mirai-core-jvm")
        }
    }
}
group = "com.yulin"
version = "1.0.0"
buildConfig {
    className("BuildConfig")
    packageName("com.yulin.cg")
    buildConfigField("String", "yulinVersion", "\"${version}\"")
    buildConfigField("String", "name", "\"图片审核\"")
    buildConfigField("String", "id", "\"com.yulin.ImageCheck\"")
}


repositories {
    mavenLocal()
//    maven("https://maven.aliyun.com/repository/gradle-plugin")
//    maven("https://maven.aliyun.com/repository/central")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
    mavenCentral()
}
dependencies {

    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("org.apache.httpcomponents.client5:httpclient5:5.3.1")
    implementation("org.jsoup:jsoup:1.15.3")
    // https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.14")
    implementation("com.alibaba:fastjson:2.0.0")
    testConsoleRuntime("top.mrxiaom:overflow-core:2.16.0-d59ef26-SNAPSHOT")
    implementation("org.bytedeco:javacv:1.5.7")
    implementation("org.bytedeco:javacpp:1.5.7")
    implementation("org.bytedeco:ffmpeg:5.0-1.5.7")
    implementation("com.baidu.aip:java-sdk:4.16.17")

    implementation("com.madgag:animated-gif-lib:1.4")
    compileOnly("org.bytedeco:javacv-platform:1.5.7")
//    compileOnly("xyz.cssxsh.mirai:mirai-hibernate-plugin:2.8.0")

//    compileOnly
    implementation(kotlin("stdlib-jdk8"))
}

noArg {
    annotation("com.yulin.anno.NoArgOpenDataClass")
}

allOpen{
    annotation("com.yulin.anno.NoArgOpenDataClass")
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "17"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "17"
}

