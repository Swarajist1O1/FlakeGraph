class DummyClass_84637 {
    @Test
    public void testGetAllChildrenNumberAsync() throws IOException, KeeperException, InterruptedException {

        final CountDownLatch doneProcessing = new CountDownLatch(1);

        zk.getAllChildrenNumber("/", (rc, path, ctx, number) -> {
            if (path == null) {
                fail((String.format("the path of getAllChildrenNumber was null.")));
            }
            assertEquals(13, number);
            doneProcessing.countDown();
        }, null);
        long waitForCallbackSecs = 2L;
        if (!doneProcessing.await(waitForCallbackSecs, TimeUnit.SECONDS)) {
            fail(String.format("getAllChildrenNumber didn't callback within %d seconds", waitForCallbackSecs));
        }
    }

}