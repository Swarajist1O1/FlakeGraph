class DummyClass_77126 {
    @Test
    public void testGetVersionFlags() {
        SpecVersion ds = new SpecVersion();

        long numericVersionCode = SpecVersion.VersionFlag.V201909.getVersionFlagValue()
                | SpecVersion.VersionFlag.V6.getVersionFlagValue()
                | SpecVersion.VersionFlag.V7.getVersionFlagValue();  // 14

        Set versionFlags = ds.getVersionFlags(numericVersionCode);

        assert !versionFlags.contains(SpecVersion.VersionFlag.V4);
        assert versionFlags.contains(SpecVersion.VersionFlag.V6);
        assert versionFlags.contains(SpecVersion.VersionFlag.V7);
        assert versionFlags.contains(SpecVersion.VersionFlag.V201909);

    }

}