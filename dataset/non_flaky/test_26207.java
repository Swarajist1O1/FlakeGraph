class DummyClass_26207 {
    @Test
    public void testIsHostUp() throws UnknownHostException
    {
        InetAddress expectedAddress = InetAddress.getLocalHost();
        Host expectedHost = mock(Host.class);

        List<String> expectedLiveNodes = Collections.singletonList(expectedAddress.getHostName());
        List<String> expectedUnreachableNodes = Collections.emptyList();

        when(myJmxProxy.getLiveNodes()).thenReturn(expectedLiveNodes);
        when(myJmxProxy.getUnreachableNodes()).thenReturn(expectedUnreachableNodes);

        when(expectedHost.getBroadcastAddress()).thenReturn(expectedAddress);

        assertThat(myHostStates.isUp(expectedHost)).isTrue();
    }

}