class DummyClass_133913 {
@Test(groups = "live", testName = "QueueApiLiveTest", singleThreaded = true)
    public void init() {
        String config = payloadFromResource("/freestyle-project-sleep-task.xml");
        RequestStatus success = api.jobsApi().create(null,"QueueTest", config);
        assertTrue(success.value());

        config = payloadFromResource("/freestyle-project.xml");
        success = api.jobsApi().create(null,"QueueTestSingleParam", config);
        assertTrue(success.value());

        config = payloadFromResource("/freestyle-project-sleep-task-multiple-params.xml");
        success = api.jobsApi().create(null,"QueueTestMultipleParams", config);
        assertTrue(success.value());
    }

}