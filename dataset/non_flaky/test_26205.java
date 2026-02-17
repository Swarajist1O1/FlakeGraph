class DummyClass_26205 {
    @Test
    public void testUseNullJmxProxyFactoryShouldThrow()
    {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> HostStatesImpl.builder()
                        .withJmxProxyFactory(null)
                        .build());
    }

}