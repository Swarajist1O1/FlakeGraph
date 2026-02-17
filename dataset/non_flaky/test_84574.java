class DummyClass_84574 {
    @Test
    public void testNode() throws Exception {
        LeaderElectionSupport electionSupport = createLeaderElectionSupport();

        electionSupport.start();
        Thread.sleep(3000);
        electionSupport.stop();
    }

}