class DummyClass_77127 {
    @Test
    public void testAllVersionValue() {
        long numericVersionCode =
                SpecVersion.VersionFlag.V201909.getVersionFlagValue()
                        | SpecVersion.VersionFlag.V4.getVersionFlagValue()
                        | SpecVersion.VersionFlag.V6.getVersionFlagValue()
                        | SpecVersion.VersionFlag.V7.getVersionFlagValue();  // 15
        Assertions.assertEquals(numericVersionCode, 15);

    }

}