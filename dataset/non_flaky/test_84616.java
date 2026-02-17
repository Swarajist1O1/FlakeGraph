class DummyClass_84616 {
    @Test
    public void testGetEphemeralsByPath() throws IOException, KeeperException, InterruptedException {

        final CountDownLatch doneProcessing = new CountDownLatch(1);
        final String checkPath = BASE + "0";
        final List<String> unexpectedBehavior = new ArrayList<String>();
        zk.getEphemerals(checkPath, (rc, ctx, paths) -> {
            if (paths == null) {
                unexpectedBehavior.add(String.format("Expected ephemeral count for %s to be %d but was null", checkPath, expected.length));
            } else if (paths.size() != EPHEMERAL_CNT) {
                unexpectedBehavior.add(String.format("Expected ephemeral count for %s to be %d but was %d", checkPath, EPHEMERAL_CNT, paths.size()));
            }
            for (int i = 0; i < EPHEMERAL_CNT; i++) {
                String path = expected[i];
                if (!paths.contains(path)) {
                    unexpectedBehavior.add(String.format("Expected path=%s didn't exist "
                                                                 + "in getEphemerals list.", path));
                }
            }
            doneProcessing.countDown();
        }, null);
        long waitForCallbackSecs = 2L;
        if (!doneProcessing.await(waitForCallbackSecs, TimeUnit.SECONDS)) {
            fail(String.format("getEphemerals(%s) didn't callback within %d seconds", checkPath, waitForCallbackSecs));
        }
        checkForUnexpectedBehavior(unexpectedBehavior);
    }

}