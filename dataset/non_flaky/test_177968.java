class DummyClass_177968 {
    @Test
    public void testColorToHSL() {
        for (TestEntry entry : sEntryList) {
            verifyColorToHSL(entry.rgb, entry.hsl);
        }
    }

}