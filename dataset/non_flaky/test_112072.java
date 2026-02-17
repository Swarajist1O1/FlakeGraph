class DummyClass_112072 {
    @Test
    public void assertJobInit() {
        while (!FooSimpleElasticJob.isCompleted()) {
            WaitingUtils.waitingShortTime();
        }
        assertTrue(getRegCenter().isExisted("/" + getJobName() + "/sharding"));
    }

}