package dev.olshevski.versions

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class VersionStabilityLevelTest {

    @Test
    fun release() {
        listOf(
            "1.0.0",
            "1.0.0-release",
            "1.0.0-RELEASE",
            "1.0.0-ga",
            "1.0.0-GA",
            "1.0.0-final",
            "1.0.0-FINAL",
            "1.0.0-r",
            "1.0.0-R"
        ).forEach {
            assertThat(stabilityLevel(it)).isEqualTo(VersionStabilityLevel.RELEASE)
        }
    }

    @Test
    fun rc() {
        listOf(
            "1.0.0-rc",
            "1.0.0-RC",
            "1.0.0-m",
            "1.0.0-M",
            "1.0.0-m1",
            "1.0.0-M1",
        ).forEach {
            assertThat(stabilityLevel(it)).isEqualTo(VersionStabilityLevel.RC)
        }
    }

    @Test
    fun beta() {
        listOf(
            "1.0.0-beta",
            "1.0.0-BETA",
            "1.0.0-beta1",
            "1.0.0-BETA1",
        ).forEach {
            assertThat(stabilityLevel(it)).isEqualTo(VersionStabilityLevel.BETA)
        }
    }

    @Test
    fun alpha() {
        listOf(
            "1.0.0-alpha",
            "1.0.0-ALPHA",
            "1.0.0-alpha1",
            "1.0.0-ALPHA1",
        ).forEach {
            assertThat(stabilityLevel(it)).isEqualTo(VersionStabilityLevel.ALPHA)
        }
    }

    @Test
    fun unknown() {
        listOf(
            "1.0.0-unknown"
        ).forEach {
            assertThat(stabilityLevel(it)).isEqualTo(VersionStabilityLevel.UNKNOWN)
        }
    }

}