class DummyClass_112065 {
    @Test
    public void assertJobInit() {
        while (!OneOffDataflowElasticJob.isCompleted()) {
            WaitingUtils.waitingShortTime();
        }
        assertTrue(getRegCenter().isExisted("/" + getJobName() + "/sharding"));
    }

}