import java.nio.file.Files
import java.nio.file.StandardOpenOption

plugins {
  id("application")
  java
}

dependencies {
  implementation(rootProject)
}

sourceSets {
  named("main") {
    java.srcDirs(
      "../src/main/java",
      "../src/game/java",
      "../src/awt_desktop/java"
    )
		resources.srcDirs(
			"../src/main/resources"
		)
  }
}

application {
  mainClass = "dev.colbster937.eagler.Main"
}

val jarName = "Minecraft4k"

tasks.jar {
  outputs.upToDateWhen { false }

  manifest {
    archiveBaseName.set(jarName)
    attributes["Main-Class"] = "dev.colbster937.eagler.Main"
  }

  doLast {
    mkdir("dist")
    var jar = file("build/libs/$jarName.jar").readBytes()
    Files.write(file("dist/$jarName.jar").toPath(), jar, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
    delete("build")
    delete("bin")
  }
}