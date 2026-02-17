class DummyClass_177973 {
    @Test
    public void testLABToXYZ() {
        for (TestEntry entry : sEntryList) {
            verifyLABToXYZ(entry.lab, entry.xyz);
        }
    }

}