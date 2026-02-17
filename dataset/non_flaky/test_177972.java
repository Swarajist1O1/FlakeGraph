class DummyClass_177972 {
    @Test
    public void testColorToLAB() {
        for (TestEntry entry : sEntryList) {
            verifyColorToLAB(entry.rgb, entry.lab);
        }
    }

}