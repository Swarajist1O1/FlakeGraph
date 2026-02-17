class DummyClass_99781 {
    @Test
    public void testUpdatesBackPressureOnSendWhenEnabledAndWithSupportedCallback() throws UnknownHostException
    {
        MockBackPressureStrategy.MockBackPressureState backPressureState = (MockBackPressureStrategy.MockBackPressureState) messagingService.getBackPressureState(InetAddressAndPort.getByName("127.0.0.2"));
        RequestCallback bpCallback = new BackPressureCallback();
        RequestCallback noCallback = new NoBackPressureCallback();
        Message<?> ignored = null;

        DatabaseDescriptor.setBackPressureEnabled(true);
        messagingService.updateBackPressureOnSend(InetAddressAndPort.getByName("127.0.0.2"), noCallback, ignored);
        assertFalse(backPressureState.onSend);

        DatabaseDescriptor.setBackPressureEnabled(false);
        messagingService.updateBackPressureOnSend(InetAddressAndPort.getByName("127.0.0.2"), bpCallback, ignored);
        assertFalse(backPressureState.onSend);

        DatabaseDescriptor.setBackPressureEnabled(true);
        messagingService.updateBackPressureOnSend(InetAddressAndPort.getByName("127.0.0.2"), bpCallback, ignored);
        assertTrue(backPressureState.onSend);
    }

}