class DummyClass_43026 {
    @Test
    public void testDefaults()
    {
        assertRecordedDefaults(recordDefaults(DevelopmentLoaderConfig.class)
                .setPlugins("")
                .setMavenLocalRepository(ArtifactResolver.USER_LOCAL_REPO)
                .setMavenRemoteRepository(ArtifactResolver.MAVEN_CENTRAL_URI));
    }

}