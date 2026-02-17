class DummyClass_99785 {
    @Test
    public void testDoesntApplyBackPressureToBroadcastAddress() throws UnknownHostException
    {
        DatabaseDescriptor.setBackPressureEnabled(true);
        messagingService.applyBackPressure(Arrays.asList(InetAddressAndPort.getByName("127.0.0.1")), ONE_SECOND);
        assertFalse(MockBackPressureStrategy.applied);
    }

}