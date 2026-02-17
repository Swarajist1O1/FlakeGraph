class DummyClass_98606 {
    @Test
    public void testIssue168() {
        assertEquals(El.eval("0.1354*((70-8)%70)*100"), 0.1354 * ((70 - 8) % 70) * 100);
        assertEquals(El.eval("0.1354*((70d-8)/70)*100"), 0.1354 * ((70d - 8) / 70) * 100);
        assertEquals(El.eval("0.5006*(70/600*100)"), 0.5006 * (70 / 600 * 100));
    }

}