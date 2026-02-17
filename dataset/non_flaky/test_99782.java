class DummyClass_99782 {
    @Test
    public void testUpdatesBackPressureOnReceiveWhenEnabledAndWithSupportedCallback() throws UnknownHostException
    {
        MockBackPressureStrategy.MockBackPressureState backPressureState = (MockBackPressureStrategy.MockBackPressureState) messagingService.getBackPressureState(InetAddressAndPort.getByName("127.0.0.2"));
        RequestCallback bpCallback = new BackPressureCallback();
        RequestCallback noCallback = new NoBackPressureCallback();
        boolean timeout = false;

        DatabaseDescriptor.setBackPressureEnabled(true);
        messagingService.updateBackPressureOnReceive(InetAddressAndPort.getByName("127.0.0.2"), noCallback, timeout);
        assertFalse(backPressureState.onReceive);
        assertFalse(backPressureState.onTimeout);

        DatabaseDescriptor.setBackPressureEnabled(false);
        messagingService.updateBackPressureOnReceive(InetAddressAndPort.getByName("127.0.0.2"), bpCallback, timeout);
        assertFalse(backPressureState.onReceive);
        assertFalse(backPressureState.onTimeout);

        DatabaseDescriptor.setBackPressureEnabled(true);
        messagingService.updateBackPressureOnReceive(InetAddressAndPort.getByName("127.0.0.2"), bpCallback, timeout);
        assertTrue(backPressureState.onReceive);
        assertFalse(backPressureState.onTimeout);
    }

}