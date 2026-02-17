class DummyClass_26209 {
    @Test
    public void testNodeIsNotRefreshed() throws UnknownHostException
    {
        final InetAddress expectedAddress = InetAddress.getLocalHost();

        when(myJmxProxy.getLiveNodes()).thenAnswer(new Answer<List<String>>()
        {
            private int counter = 0;

            @Override
            public List<String> answer(InvocationOnMock invocation)
            {
                if (counter++ == 2)
                {
                    return Collections.singletonList(expectedAddress.getHostAddress());
                }

                return Collections.emptyList();
            }

}