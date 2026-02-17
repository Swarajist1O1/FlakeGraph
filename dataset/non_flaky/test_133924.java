class DummyClass_133924 {
@Test(groups = "unit", testName = "JobsApiMockTest")
    public void testGetInnerFolderJobList() throws Exception {
        MockWebServer server = mockWebServer();

        String body = payloadFromResource("/jobsInJenkinsFolder.json");
        server.enqueue(new MockResponse().setBody(body).setResponseCode(200));
        JenkinsApi jenkinsApi = api(server.url("/").url());
        JobsApi api = jenkinsApi.jobsApi();
        try {
            JobList output = api.jobList("Folder1/Folder 2");
            assertNotNull(output);
            assertNotNull(output.jobs());
            assertEquals(output.jobs().size(), 1);
            assertEquals(output.jobs().get(0), Job.create("hudson.model.FreeStyleProject", "Test Project", "http://localhost:8080/job/username", null));
            assertSent(server, "GET", "/job/Folder1/job/Folder%202/api/json");
        } finally {
            jenkinsApi.close();
            server.shutdown();
        }
    }

}