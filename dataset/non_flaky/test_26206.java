class DummyClass_26206 {
    @Test
    public void testIsInetAddressUp() throws UnknownHostException
    {
        InetAddress expectedAddress = InetAddress.getLocalHost();

        List<String> expectedLiveNodes = Collections.singletonList(expectedAddress.getHostName());
        List<String> expectedUnreachableNodes = Collections.emptyList();

        when(myJmxProxy.getLiveNodes()).thenReturn(expectedLiveNodes);
        when(myJmxProxy.getUnreachableNodes()).thenReturn(expectedUnreachableNodes);

        assertThat(myHostStates.isUp(expectedAddress)).isTrue();
    }

}