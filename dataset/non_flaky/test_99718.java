class DummyClass_99718 {
    @Test
    public void parsesVersionNoMatch() throws Exception
    {
        String versionString = SettingsMisc.parseVersionFile("VersionFileChangedFormat :(");
        assertEquals("Unable to find version information", versionString);
    }

}