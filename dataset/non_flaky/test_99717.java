class DummyClass_99717 {
    @Test
    public void parsesVersionMatch() throws Exception
    {
        String versionString = SettingsMisc.parseVersionFile("CassandraVersion=TheBestVersion\n");
        assertEquals("Version: TheBestVersion", versionString);
    }

}