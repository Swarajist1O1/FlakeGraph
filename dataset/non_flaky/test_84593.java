class DummyClass_84593 {
    @Test
    public void testInt() {
        final int expected = 4;
        final String tag = "tag1";
        checkWriterAndReader(
                (oa) -> oa.writeInt(expected, tag),
                (ia) -> {
                    int actual = ia.readInt(tag);
                    assertEquals(expected, actual);
                }
        );
    }

}