class DummyClass_38273 {
    @Test
    public void testSpecialValues() {
        String eight = "00000000";
        String sixteen = eight + eight;
        putDirect("row1", "col1", eight, 0);
        putDirect("row2", "col1", sixteen, 0);
        Pair<String, Long> direct1 = getDirect("row1", "col1", 1);
        assertEquals(eight, direct1.lhSide);
        Pair<String, Long> direct2 = getDirect("row2", "col1", 1);
        assertEquals(sixteen, direct2.lhSide);
    }

}