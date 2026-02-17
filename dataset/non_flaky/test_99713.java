class DummyClass_99713 {
    @Test
    public void testDefaults() throws Exception
    {
        SettingsNode settingsNode = new SettingsNode(new SettingsNode.Options());
        assertEquals(null, settingsNode.datacenter);
    }

}