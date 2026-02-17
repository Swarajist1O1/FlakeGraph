class DummyClass_99714 {
    @Test
    public void testOveridingDataCenter() throws Exception
    {
        SettingsNode.Options options = new SettingsNode.Options();
        options.accept("datacenter=dc1");
        SettingsNode settingsNode = new SettingsNode(options);
        assertEquals("dc1", settingsNode.datacenter);
    }

}