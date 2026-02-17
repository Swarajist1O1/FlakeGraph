class DummyClass_84581 {
    @Test
    public void testReadyOffer() throws Exception {
        final ArrayList<EventType> events = new ArrayList<>();
        final CountDownLatch electedComplete = new CountDownLatch(1);

        final LeaderElectionSupport electionSupport1 = createLeaderElectionSupport();
        electionSupport1.start();
        LeaderElectionSupport electionSupport2 = createLeaderElectionSupport();
        LeaderElectionAware listener = new LeaderElectionAware() {
            boolean stoppedElectedNode = false;
            @Override
            public void onElectionEvent(EventType eventType) {
                events.add(eventType);
                if (!stoppedElectedNode
                    && eventType == EventType.DETERMINE_COMPLETE) {
                    stoppedElectedNode = true;
                    try {
                        // stopping the ELECTED node, so re-election will happen.
                        electionSupport1.stop();
                    } catch (Exception e) {
                        LOGGER.error("Unexpected exception", e);
                    }
                }
                if (eventType == EventType.ELECTED_COMPLETE) {
                    electedComplete.countDown();
                }
            }

}