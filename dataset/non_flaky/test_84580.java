class DummyClass_84580 {
    @Test
    public void testGetLeaderHostName() throws Exception {
        LeaderElectionSupport electionSupport = createLeaderElectionSupport();

        electionSupport.start();

        // Sketchy: We assume there will be a leader (probably us) in 3 seconds.
        Thread.sleep(3000);

        String leaderHostName = electionSupport.getLeaderHostName();

        assertNotNull(leaderHostName);
        assertEquals("foohost", leaderHostName);

        electionSupport.stop();
    }

}