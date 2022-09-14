package dev.olshevski.versions

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class VersionsPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            plugins.apply(com.github.benmanes.gradle.versions.VersionsPlugin::class.java)
            tasks.named("dependencyUpdates", DependencyUpdatesTask::class.java).configure { task ->
                task.rejectVersionIf {
                    stabilityLevel(it.currentVersion) > stabilityLevel(it.candidate.version)
                }
            }
        }
    }

}