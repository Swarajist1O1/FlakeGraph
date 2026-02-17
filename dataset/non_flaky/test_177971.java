class DummyClass_177971 {
    @Test
    public void testColorToXYZ() {
        for (TestEntry entry : sEntryList) {
            verifyColorToXYZ(entry.rgb, entry.xyz);
        }
    }

}