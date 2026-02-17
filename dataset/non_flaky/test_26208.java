class DummyClass_26208 {
    @Test
    public void testIsInetAddressUpFaultyNode() throws UnknownHostException
    {
        InetAddress expectedAddress = InetAddress.getLocalHost();

        when(myJmxProxy.getLiveNodes()).thenReturn(Collections.emptyList());
        when(myJmxProxy.getUnreachableNodes()).thenReturn(Collections.emptyList());

        assertThat(myHostStates.isUp(expectedAddress)).isFalse();
    }

}