plugins { kotlin("jvm"); application }
repositories { mavenCentral() }
kotlin { jvmToolchain(17) }
sourceSets {
    main { kotlin.srcDir("src/main/java"); kotlin.srcDir("../latihan") }
    test { kotlin.srcDir("src/test/java") }
}
dependencies { testImplementation("junit:junit:4.13.2") }
application { mainClass.set("com.yafi.campustasks.demo.MainKt") }
tasks.test { useJUnit(); testLogging { events("passed", "skipped", "failed") } }
// Alias untuk paket domain JVM; pada proyek Android task ini disediakan Android Gradle Plugin.
tasks.register("testDebugUnitTest") { group = "verification"; dependsOn(tasks.test) }

tasks.register<JavaExec>("runBasics") {
    group = "application"
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("KotlinBasicsKt")
}
