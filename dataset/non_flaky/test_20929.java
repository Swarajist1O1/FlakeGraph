class DummyClass_20929 {
    @Test
    public void testEquals() {
        Metric m1 = Metric.newBuilder().name("m1").tag("t1", "v1").tag("t2", "v2").value(1, 0.0).build();
        Metric m2 = Metric.newBuilder().name("m1").tag("t2", "v2").tag("t1", "v1").value(1, 0.0).build();

        assertTrue(m1.equals(m2));
        assertTrue(m2.equals(m1));

        Metric m3 = Metric.newBuilder().name("m1").tag("t1", "v1").value(1, 0.0).build();
        assertFalse(m1.equals(m3));

        Metric m4 = Metric.newBuilder().name("m4").tag("t2", "v2").tag("t1", "v1").value(1, 0.0).build();
        assertFalse(m1.equals(m4));

        Metric m5 = Metric.newBuilder().name("m1").tag("t3", "v3").tag("t2", "v2").tag("t1", "v1").value(1, 0.0)
                .build();
        assertFalse(m1.equals(m5));

        Metric m6 = Metric.newBuilder().name("m1").tag("t2", "v2").tag("t1", "v1").value(2, 0.0).build();
        assertFalse(m1.equals(m6));

        Metric m7 = Metric.newBuilder().name("m1").tag("t2", "v2").tag("t1", "v1").value(1, 1.0).build();
        assertFalse(m1.equals(m7));
    }

}