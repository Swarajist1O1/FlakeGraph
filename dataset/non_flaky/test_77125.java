class DummyClass_77125 {
    @Test
    public void testGetVersionValue() {
        SpecVersion ds = new SpecVersion();
        Set versionFlags = EnumSet.of(
                SpecVersion.VersionFlag.V4,
                SpecVersion.VersionFlag.V201909);
        Assertions.assertEquals(ds.getVersionValue(versionFlags), 9); // 0001|1000
    }

}