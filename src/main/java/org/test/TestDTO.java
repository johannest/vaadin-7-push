package org.test;

import java.util.UUID;

public class TestDTO {

    private String foo;
    private String bar;
    private String baz;

    public TestDTO() {
        this.foo = UUID.randomUUID().toString();
        this.bar = UUID.randomUUID().toString();
        this.baz = UUID.randomUUID().toString();
    }
}
