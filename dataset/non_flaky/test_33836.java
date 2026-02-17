class DummyClass_33836 {
    @BeforeEach
    public void clean() {
        template.sendBodyAndHeader(ironMQEndpoint, "fo", IronMQConstants.OPERATION, IronMQConstants.CLEARQUEUE);
        deleteDirectory("target/out");
    }

}