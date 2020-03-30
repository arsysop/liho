/********************************************************************************
 * Copyright (c) 2020 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   ArSysOp - initial API and implementation
 ********************************************************************************/

plugins {
    java
    jacoco
    `maven-publish`
}

group = "ru.arsysop"
version = "0.1"

project.apply {
    description =
        "License Header verification tool: checks if license header in all your project source base file fits Eclipse Foundation Project Handbook (https://www.eclipse.org/projects/handbook/#ip-copyright-headers"
}

repositories {
    mavenCentral()
    mavenLocal()
    jcenter()
}

dependencies {
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

tasks.withType(Test::class) {
    useJUnitPlatform()
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

}

fun extendManifest(mf: Manifest): Unit {
    mf.attributes(
        "group" to project.group,
        "artifact" to project.name,
        "version" to project.version,
        "vendor" to "ArSysOp"
    )
}

publishing {
    /*repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/arsysop/liho")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }*/
    publications {
        repositories {
            maven {
                url = uri("$buildDir/repos")
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
                        name.set("Eclipse Public License 2.0")
                        url.set("https://spdx.org/licenses/EPL-2.0.html")
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
