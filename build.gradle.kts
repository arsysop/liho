/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
plugins {
    java
    jacoco
    `maven-publish`
    //id("ru.arsysop.liho.liho-gradle-plugin") version "0.1"
}

group = "ru.arsysop.liho"
version = "0.1"

project.apply {
    description =
        "License Header verification tool: checks if license header in all your project source base file fits Eclipse Foundation Project Handbook (https://www.eclipse.org/projects/handbook/#ip-copyright-headers"
}

repositories {
    mavenCentral()
    jcenter()
    maven(url = "https://dl.bintray.com/arsysop/lang")
}

dependencies {
    implementation("ru.arsysop:ru.arsysop.lang:0.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

java {
    withJavadocJar()
    withSourcesJar()
}

/*
liho {
    root.set(project.file("src/main"))
    strict.set(true)
    report.set(project.file("$buildDir/liho/report.txt"))
}
*/

tasks.withType(Test::class) {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport")
}

jacoco {
    toolVersion = "0.8.5"
}

tasks.jacocoTestReport {
    reports {
        csv.isEnabled = false
        html.isEnabled = false
        xml.isEnabled = true
        xml.destination = file("${buildDir}/test-coverage.xml")
    }
}

tasks.jar {
    extendManifest(manifest)
}

tasks.withType(Jar::class) {
    extendManifestShort(manifest)
    from("README.md", "LICENSE")
}

tasks.getByName("sourcesJar") {
    (this as Jar).from(sourceSets["test"].allSource)
}

fun extendManifest(mf: Manifest) {
    mf.attributes(
        "Bundle-Vendor" to "ArSysOp",
        "Bundle-Name" to "ru.arsysop.liho",
        "Bundle-SymbolicName" to "ru.arsysop.liho",
        "Bundle-Version" to project.version,
        "Automatic-Module-Name" to "ru.arsysop.liho",
        "Bundle-ManifestVersion" to "2",
        "Bundle-RequiredExecutionEnvironment" to "JavaSE-1.8",
        "Export-Package" to
                listOf("ru.arsysop.liho.report", "ru.arsysop.liho.bulk")
                    .map { it + ";version=${project.version}" }
                    .joinToString(", ")
    )
}

fun extendManifestShort(mf: Manifest) {
    mf.attributes(
        "Group" to project.group,
        "Artifact" to project.name,
        "Version" to project.version
    )
}

publishing {
    publications {
        repositories {
            maven {
                url = uri("$buildDir/local-repo")
            }
        }
        register<MavenPublication>("gpr") {
            from(components["java"])
            pom {
                name.set(project.name)
                description.set(project.description)
                url.set("https://github.com/ArSysOp/liho")
                licenses {
                    license {
                        name.set("Apache 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0")
                    }
                }
                developers {
                    developer {
                        id.set("eparovyhsnaya")
                        name.set("Elena Parovyshnaia")
                        email.set("elena.parovyshnaya@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/arsysop/liho.git")
                    developerConnection.set("scm:git:ssh://github.com/arsysop/liho.git")
                    url.set("https://github.com/arsysop/liho")
                }
            }
        }
    }
}
