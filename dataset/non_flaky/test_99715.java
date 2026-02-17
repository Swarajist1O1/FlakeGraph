class DummyClass_99715 {
    @Test
    public void versionTriggersSpecialOption() throws Exception
    {
        assertTrue(SettingsMisc.maybeDoSpecial(ImmutableMap.of("version", new String[] {})));
    }

}