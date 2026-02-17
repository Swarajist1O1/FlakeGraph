class DummyClass_122619 {
    @Test
    public void testDelete() {
        write(0, 0, "foo\nbar\nzoo\n");
        delete(0, 2, 2, 1);
        assertEquals("fooo\n", textBuffer.getString());

        delete(0, 4, 1, 0);
        assertEquals("fooo", textBuffer.getString());

        delete(0, 0, 0, 4);
        assertEquals("", textBuffer.getString());

        delete(0, 0, 0, 0);
        assertEquals("", textBuffer.getString());
    }

}