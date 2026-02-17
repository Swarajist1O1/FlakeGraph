class DummyClass_43027 {
    @Test
    public void testExplicitPropertyMappings()
    {
        Map<String, String> properties = new ImmutableMap.Builder<String, String>()
                .put("plugin.bundles", "a,b,c")
                .put("maven.repo.local", "local-repo")
                .put("maven.repo.remote", "remote-a,remote-b")
                .buildOrThrow();

        DevelopmentLoaderConfig expected = new DevelopmentLoaderConfig()
                .setPlugins(ImmutableList.of("a", "b", "c"))
                .setMavenLocalRepository("local-repo")
                .setMavenRemoteRepository(ImmutableList.of("remote-a", "remote-b"));

        assertFullMapping(properties, expected);
    }

}