class DummyClass_91412 {
    @Test
    public void testPluginInstalled() {
        try (TransportClient client = new PreBuiltXPackTransportClient(Settings.EMPTY)) {
            Settings settings = client.settings();
            assertEquals(SecurityField.NAME4, NetworkModule.TRANSPORT_TYPE_SETTING.get(settings));
        }
    }

}