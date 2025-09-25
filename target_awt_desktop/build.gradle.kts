plugins {
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
  }
}

tasks.jar {
  manifest {
    attributes["Main-Class"] = "dev.colbster937.eagler.Main"
  }
}