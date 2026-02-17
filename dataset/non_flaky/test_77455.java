class DummyClass_77455 {
    @Test
    public void testInstallPluginTwice() {
        for (Class<? extends Plugin> plugin :
                Arrays.asList(ParentJoinPlugin.class, ReindexPlugin.class, PercolatorPlugin.class,
                    MustachePlugin.class)) {
            try {
                new PreBuiltTransportClient(Settings.EMPTY, plugin);
                fail("exception expected");
            } catch (IllegalArgumentException ex) {
                assertTrue("Expected message to start with [plugin already exists: ] but was instead [" + ex.getMessage() + "]",
                        ex.getMessage().startsWith("plugin already exists: "));
            }
        }
    }

}