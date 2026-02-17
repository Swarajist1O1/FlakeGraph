class DummyClass_84594 {
    @Test
    public void testBool() {
        final boolean expected = false;
        final String tag = "tag1";
        checkWriterAndReader(
                (oa) -> oa.writeBool(expected, tag),
                (ia) -> {
                    boolean actual = ia.readBool(tag);
                    assertEquals(expected, actual);
                }
        );
    }

}