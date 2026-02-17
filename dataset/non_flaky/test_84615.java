class DummyClass_84615 {
    @Test
    public void testGetEphemerals() throws IOException, KeeperException, InterruptedException {

        final CountDownLatch doneProcessing = new CountDownLatch(1);
        final List<String> unexpectedBehavior = new ArrayList<String>();
        zk.getEphemerals((rc, ctx, paths) -> {
            if (paths == null) {
                unexpectedBehavior.add(String.format("Expected ephemeral count for"
                                                             + " allPaths to be %d but was null", expected.length));
            } else if (paths.size() != expected.length) {
                unexpectedBehavior.add(String.format("Expected ephemeral count for allPaths to be %d but was %d", expected.length, paths.size()));
            }
            for (int i = 0; i < expected.length; i++) {
                String path = expected[i];
                if (!paths.contains(path)) {
                    unexpectedBehavior.add(String.format("Path=%s exists in getEphemerals list ", path));
                }
            }
            doneProcessing.countDown();
        }, null);
        long waitForCallbackSecs = 2L;
        if (!doneProcessing.await(waitForCallbackSecs, TimeUnit.SECONDS)) {
            fail(String.format("getEphemerals didn't callback within %d seconds", waitForCallbackSecs));
        }
        checkForUnexpectedBehavior(unexpectedBehavior);

    }

}