class DummyClass_84596 {
    @Test
    public void testFloat() {
        final float expected = 3.14159f;
        final String tag = "tag1";
        final float delta = 1e-10f;
        checkWriterAndReader(
                (oa) -> oa.writeFloat(expected, tag),
                (ia) -> {
                    float actual = ia.readFloat(tag);
                    assertEquals(expected, actual, delta);
                }
        );
    }

}