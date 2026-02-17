class DummyClass_91415 {
@TestLogging("org.elasticsearch.xpack.core.ssl.SSLService:TRACE")
    public void init() throws Exception {
        Path caPath = getDataPath(LDAPCACERT_PATH);
        /*
         * Prior to each test we reinitialize the socket factory with a new SSLService so that we get a new SSLContext.
         * If we re-use a SSLContext, previously connected sessions can get re-established which breaks hostname
         * verification tests since a re-established connection does not perform hostname verification.
         */
        globalSettings = Settings.builder()
            .put("path.home", createTempDir())
            .put("xpack.ssl.certificate_authorities", caPath)
            .build();
        threadPool = new TestThreadPool("LdapUserSearchSessionFactoryTests");
    }

}