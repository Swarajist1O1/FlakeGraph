class DummyClass_112066 {
    @Test
    public void assertJobInit() {
        while (!StreamingDataflowElasticJobForExecuteThrowsException.isCompleted()) {
            WaitingUtils.waitingShortTime();
        }
        assertTrue(getRegCenter().isExisted("/" + getJobName() + "/sharding"));
    }

}