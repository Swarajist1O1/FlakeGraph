class DummyClass_112069 {
    @Test
    public void assertJobInit() {
        while (!StreamingDataflowElasticJobForExecuteFailure.isCompleted()) {
            WaitingUtils.waitingShortTime();
        }
        assertTrue(getRegCenter().isExisted("/" + getJobName() + "/sharding"));
    }

}