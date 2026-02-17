class DummyClass_91422 {
@TestLogging("org.elasticsearch.xpack.ssl.RestrictedTrustManager:DEBUG")
    public Settings nodeSettings(int nodeOrdinal) {

        Settings parentSettings = super.nodeSettings(nodeOrdinal);
        Settings.Builder builder = Settings.builder()
                .put(parentSettings.filter((s) -> s.startsWith("xpack.ssl.") == false))
                .put(nodeSSL);

        restrictionsPath = configPath.resolve("trust_restrictions.yml");
        restrictionsTmpPath = configPath.resolve("trust_restrictions.tmp");

        writeRestrictions("*.trusted");
        builder.put("xpack.ssl.trust_restrictions.path", restrictionsPath);
        builder.put("resource.reload.interval.high", RESOURCE_RELOAD_MILLIS + "ms");

        return builder.build();
    }

}