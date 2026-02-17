class DummyClass_84597 {
    @Test
    public void testDouble() {
        final double expected = 3.14159f;
        final String tag = "tag1";
        final float delta = 1e-20f;
        checkWriterAndReader(
                (oa) -> oa.writeDouble(expected, tag),
                (ia) -> {
                    double actual = ia.readDouble(tag);
                    assertEquals(expected, actual, delta);
                }
        );
    }

}