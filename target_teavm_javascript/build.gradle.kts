import java.nio.file.Files
import java.nio.file.StandardOpenOption

plugins {
  java
  id("org.teavm") version "0.12.3"
}

dependencies {
  implementation(rootProject)
  implementation("org.teavm:teavm-core:0.12.3")
}

sourceSets {
  named("main") {
    java.srcDirs(
      "../src/main/java",
      "../src/game/java",
      "../src/teavm/java",
      "../src/teavm_js/java",
    )
  }
}

val jsFolder = "javascript"
val jsFileName = "classes.js"
val debug = project.hasProperty("debug")

teavm.js {
	obfuscated = !debug
	sourceMap = debug
	targetFileName = "../$jsFileName"
	optimization = org.teavm.gradle.api.OptimizationLevel.valueOf(if (debug) "NONE" else "AGGRESSIVE")
	outOfProcess = false
	fastGlobalAnalysis = false
	processMemory = 512
	entryPointName.set("main")
	mainClass = "dev.colbster937.eagler.Main"
	outputDir = file(jsFolder)
	debugInformation = debug
}

tasks.named("generateJavaScript") {
  doFirst {
    delete("$jsFolder/$jsFileName")
    delete("$jsFolder/$jsFileName.map")
    delete("$jsFolder/$jsFileName.teavmdbg")
    delete("$jsFolder/Minecraft4k.html")
    delete("$jsFolder/js")
  }

  doLast {
    var js = file("$jsFolder/$jsFileName").readText(Charsets.UTF_8)
    var html = file("$jsFolder/index.html").readText(Charsets.UTF_8)

    js = js.replace(Regex("""(?m)^//# sourceMappingURL=.*$"""), "")
    html = html.replace("<style>", "<script>\n$js\n  </script>\n  <style>").replace("<script src=\"$jsFileName\"></script>\n  ", "")
	
    Files.write(file("$jsFolder/Minecraft4k.html").toPath(), html.toByteArray(Charsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)

    delete("$jsFolder/js")
  }
}