class DummyClass_112064 {
    @Test
    public void assertJobInit() {
        while (!StreamingDataflowElasticJob.isCompleted()) {
            WaitingUtils.waitingShortTime();
        }
        assertTrue(getRegCenter().isExisted("/" + getJobName() + "/sharding"));
    }

}