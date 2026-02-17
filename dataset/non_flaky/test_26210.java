class DummyClass_26210 {
    @Test
    public void testNodeIsRefreshed() throws UnknownHostException
    {
        final InetAddress expectedAddress = InetAddress.getLocalHost();

        HostStatesImpl hostStates = HostStatesImpl.builder()
                .withJmxProxyFactory(myJmxProxyFactory)
                .withRefreshIntervalInMs(1)
                .build();

        when(myJmxProxy.getLiveNodes()).thenAnswer(new Answer<List<String>>()
        {
            private int counter = 0;

            @Override
            public List<String> answer(InvocationOnMock invocation)
            {
                if (counter++ == 1)
                {
                    return Collections.singletonList(expectedAddress.getHostAddress());
                }
                return Collections.emptyList();
            }

}