class DummyClass_122618 {
    @Test
    public void testWrite() {
        assertEquals("", textBuffer.getString());
        assertWrite(2, 0, "foo\nbar\n",
                0, 0, "foo\nbar\n");

        assertWrite(1, 6, "fofirst\nsecondo\nbar\n",
                0, 2, "first\nsecond");

        assertWrite(3, 1, "fofirst\nsecondo\nbar\na",
                3, 0, "a");
        assertWrite(4, 0, "fofirst\nsecondo\nbar\na\n",
                3, 1, "\n");
    }

}