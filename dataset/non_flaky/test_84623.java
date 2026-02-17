class DummyClass_84623 {
    @Test
    public void testJvmPauseMonitorConfigured() {
        final Long sleepTime = 444L;
        final Long warnTH = 5555L;
        final Long infoTH = 555L;

        QuorumPeerConfig qpConfig = mock(QuorumPeerConfig.class);
        when(qpConfig.isJvmPauseMonitorToRun()).thenReturn(true);
        when(qpConfig.getJvmPauseSleepTimeMs()).thenReturn(sleepTime);
        when(qpConfig.getJvmPauseWarnThresholdMs()).thenReturn(warnTH);
        when(qpConfig.getJvmPauseInfoThresholdMs()).thenReturn(infoTH);

        serverConfig.readFrom(qpConfig);

        assertEquals(sleepTime, Long.valueOf(serverConfig.getJvmPauseSleepTimeMs()));
        assertEquals(warnTH, Long.valueOf(serverConfig.getJvmPauseWarnThresholdMs()));
        assertEquals(infoTH, Long.valueOf(serverConfig.getJvmPauseInfoThresholdMs()));
        assertTrue(serverConfig.isJvmPauseMonitorToRun());
    }

}