class DummyClass_134002 {
    @Test(timeout = 300_000)
    public void fileDescriptorLeaksBaseServerResetTest() {

        workflow(wf -> {
            wf.setupDocker(fixture -> fixture.getCluster().numNodes(1));
            wf.deploy();

            try {
                resourceLeaks(wf);
            } catch (Exception e) {
                fail("Test failed", e);
            }
        });
    }

}