import org.teavm.gradle.api.OptimizationLevel
import java.nio.file.Files
import java.nio.file.StandardOpenOption

plugins {
  java
	id("org.teavm") version "0.12.1"
}

repositories {
  mavenCentral()
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

sourceSets {
	named("main") {
		java.srcDirs(
			"src/main",
			"src/game",
		)
	}
}

dependencies {
  implementation("org.teavm:teavm-core:0.12.1")
}

val jsFolder = "javascript"
val jsFileName = "classes.js"

teavm.js {
	obfuscated = true
	sourceMap = false
	targetFileName = "../$jsFileName"
	optimization = OptimizationLevel.AGGRESSIVE // Change to "AGGRESSIVE" for release
	outOfProcess = false
	fastGlobalAnalysis = false
	processMemory = 512
	entryPointName.set("main")
	mainClass = "dev.colbster937.eagler.Main"
	outputDir = file(jsFolder)
	properties = mapOf("java.util.TimeZone.autodetect" to "true")
	debugInformation = false
}

tasks.named("generateJavaScript") {
  doLast {
    var html = file("$jsFolder/index.html").readText(Charsets.UTF_8)
    var js = file("$jsFolder/$jsFileName").readText(Charsets.UTF_8)

    js = js.replace(Regex("""(?m)^//# sourceMappingURL=.*$"""), "")

    html = html.replace("</style>", "</style>\n  <script>\n$js\n  </script>").replace("<script src=\"$jsFileName\"></script>\n  ", "")
	
    Files.write(file("$jsFolder/offline.html").toPath(), html.toByteArray(Charsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
  }
}