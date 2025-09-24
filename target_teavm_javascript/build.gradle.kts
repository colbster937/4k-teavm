import java.nio.file.Files
import java.nio.file.StandardOpenOption
import java.util.Base64

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
  outputs.upToDateWhen { false }
  
  doFirst {
    delete("$jsFolder/$jsFileName")
    delete("$jsFolder/$jsFileName.map")
    delete("$jsFolder/$jsFileName.teavmdbg")
    delete("$jsFolder/Minecraft4k.html")
    delete("$jsFolder/Minecraft4k_alt.html")
    delete("$jsFolder/js")
  }

  doLast {
    var js = file("$jsFolder/$jsFileName").readText(Charsets.UTF_8)
    var html = file("$jsFolder/index.html").readText(Charsets.UTF_8)
    var html_alt1 = html.replace("fullscreen", "fullscreen fullscreen-alt").replace("manifest.json", "manifest_alt1.json")
    var html_alt2 = html.replace("<body>", "<body style=\"display:flex;flex-direction:column;align-items:center;\">\n  <h1>Minecraft 4k</h1>").replace("fullscreen", "width=\"856\" height=\"480\"").replace("manifest.json", "manifest_alt2.json")
    var manifest = file("$jsFolder/manifest.json").readText(Charsets.UTF_8)
    var manifest_alt1 = manifest.replace("index.html", "index_alt1.html").replaceFirst("4k", "4-k (Alt #1)").replaceFirst("4k", "4k 1").replaceFirst("4-k", "4k")
    var manifest_alt2 = manifest.replace("index.html", "index_alt2.html").replaceFirst("4k", "4-k (Alt #2)").replaceFirst("4k", "4k 2").replaceFirst("4-k", "4k")
    var icon = "data:image/png;base64," + Base64.getEncoder().encodeToString(file("$jsFolder/icon.png").readBytes())

    Files.write(file("$jsFolder/index_alt1.html").toPath(), html_alt1.toByteArray(Charsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
    Files.write(file("$jsFolder/index_alt2.html").toPath(), html_alt2.toByteArray(Charsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
    Files.write(file("$jsFolder/manifest_alt1.json").toPath(), manifest_alt1.toByteArray(Charsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
    Files.write(file("$jsFolder/manifest_alt2.json").toPath(), manifest_alt2.toByteArray(Charsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)

    js = js.replace(Regex("""(?m)^//# sourceMappingURL=.*$"""), "")
    html = toOffline(html, js, icon)
    html_alt1 = toOffline(html_alt1, js, icon)
    html_alt2 = toOffline(html_alt2, js, icon)

    Files.write(file("$jsFolder/Minecraft4k.html").toPath(), html.toByteArray(Charsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
    Files.write(file("$jsFolder/Minecraft4k_alt1.html").toPath(), html_alt1.toByteArray(Charsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
    Files.write(file("$jsFolder/Minecraft4k_alt2.html").toPath(), html_alt2.toByteArray(Charsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)

    delete("$jsFolder/js")
  }
}

fun toOffline(html: String, js: String, icon: String): String {
  return html.replace("</title>", "</title>\n  <script>\n$js\n  </script>").replace("<script src=\"$jsFileName\"></script>\n  ", "").replace("<link rel=\"manifest\" href=\"manifest.json\" />\n  ", "").replace("<link rel=\"manifest\" href=\"manifest_alt1.json\" />\n  ", "").replace("<link rel=\"manifest\" href=\"manifest_alt2.json\" />\n  ", "").replace("<meta property=\"og:image\" content=\"/icon.png\" />\n  ", "").replace("icon.png", icon)
}