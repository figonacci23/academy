package com.ctw.workstation.config;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.ArrayList;
import java.util.List;

public class CtwAcademyProfile implements QuarkusTestProfile {
    @Override
    public List<TestResourceEntry> testResources() {
        return List.of(
                new TestResourceEntry(CtwAcademyResource.class),
                new TestResourceEntry(WiremockResource.class)
        );
    }
}
