class DummyClass_99801 {
    @Test (expected = IllegalArgumentException.class)
    public void build_TcpConnectTimeoutLessThanZero()
    {
        test(settings -> settings.withTcpConnectTimeoutInMS(-1));
    }

}