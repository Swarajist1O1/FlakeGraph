class DummyClass_84602 {
    @BeforeEach
    public void setup() throws Exception {
        CollectorRegistry.defaultRegistry.clear();
        provider = new PrometheusMetricsProvider();
        Properties configuration = new Properties();
        configuration.setProperty("httpPort", "0"); // ephemeral port
        configuration.setProperty("exportJvmInfo", "false");
        provider.configure(configuration);
        provider.start();
    }

}