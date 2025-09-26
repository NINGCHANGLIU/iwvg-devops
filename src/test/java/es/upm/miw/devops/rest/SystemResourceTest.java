package es.upm.miw.devops.rest;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

class SystemResourceTest {

    @Test
    void testGenerateBadgeAndInfo() {
        SystemResource resource = new SystemResource();
        ReflectionTestUtils.setField(resource, "artifact", "demo-artifact");
        ReflectionTestUtils.setField(resource, "version", "5.0.0");
        ReflectionTestUtils.setField(resource, "build", "2025-01-01 00:00:00");

        byte[] svgBytes = resource.generateBadge();
        assertThat(svgBytes).isNotNull();
        String svg = new String(svgBytes);
        assertThat(svg).startsWith("<svg");
        assertThat(svg).contains("Render");
        assertThat(svg).contains("v5.0.0");

        String info = resource.applicationInfo();
        assertThat(info).contains("demo-artifact::5.0.0::");
        assertThat(info).contains("/version-badge");
        assertThat(info).contains("/actuator/info");
        assertThat(info).contains("/swagger-ui.html");
    }
}

