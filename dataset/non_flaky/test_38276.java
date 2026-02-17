class DummyClass_38276 {
    @Test
    public void testEmptyValue() {
        putDirect("row1", "col1", "v1", 0);
        Pair<String, Long> pair = getDirect("row1", "col1", 1);
        assertEquals(0L, (long)pair.getRhSide());
        assertEquals("v1", pair.getLhSide());

        putDirect("row1", "col1", "", 2);
        pair = getDirect("row1", "col1", 2);
        assertEquals(0L, (long)pair.getRhSide());
        assertEquals("v1", pair.getLhSide());

        pair = getDirect("row1", "col1", 3);
        assertEquals(2L, (long)pair.getRhSide());
        assertEquals("", pair.getLhSide());
    }

}