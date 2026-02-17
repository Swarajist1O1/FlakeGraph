class DummyClass_33834 {
    @BeforeEach
    public void clean() {
        template.sendBodyAndHeader(ironQueue1, "fo", IronMQConstants.OPERATION, IronMQConstants.CLEARQUEUE);
        template.sendBodyAndHeader(ironQueue2, "fo", IronMQConstants.OPERATION, IronMQConstants.CLEARQUEUE);
    }

}