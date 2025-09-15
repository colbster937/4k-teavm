import org.teavm.gradle.api.OptimizationLevel

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
      "src/game"
		)
	}
}

dependencies {
  implementation("org.teavm:teavm-core:0.12.1")
}

teavm.js {
	obfuscated = true
	sourceMap = true
	targetFileName = "../classes.js"
	optimization = OptimizationLevel.BALANCED // Change to "AGGRESSIVE" for release
	outOfProcess = false
	fastGlobalAnalysis = false
	processMemory = 512
	entryPointName.set("main")
	mainClass = "dev.colbster937.eagler.Main"
	outputDir = file("javascript")
	properties = mapOf("java.util.TimeZone.autodetect" to "true")
	debugInformation = false
}