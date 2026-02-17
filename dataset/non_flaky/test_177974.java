class DummyClass_177974 {
    @Test
    public void testXYZToColor() {
        for (TestEntry entry : sEntryList) {
            verifyXYZToColor(entry.xyz, entry.rgb);
        }
    }

}