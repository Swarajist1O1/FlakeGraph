class DummyClass_99784 {
    @Test
    public void testAppliesBackPressureWhenEnabled() throws UnknownHostException
    {
        DatabaseDescriptor.setBackPressureEnabled(false);
        messagingService.applyBackPressure(Arrays.asList(InetAddressAndPort.getByName("127.0.0.2")), ONE_SECOND);
        assertFalse(MockBackPressureStrategy.applied);

        DatabaseDescriptor.setBackPressureEnabled(true);
        messagingService.applyBackPressure(Arrays.asList(InetAddressAndPort.getByName("127.0.0.2")), ONE_SECOND);
        assertTrue(MockBackPressureStrategy.applied);
    }

}