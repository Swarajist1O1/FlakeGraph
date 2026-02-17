class DummyClass_133998 {
    @Test(timeout = 300000)
    public void writeAfterResetTest() {
        workflow(wf -> {

                    wf.setupDocker(fixture -> {
                        fixture.getCluster().numNodes(1);
                    });

                    wf.deploy();
                    try {
                        writeAfterReset(wf);
                    } catch (Exception e) {
                        Assertions.fail("Test failed: " + e);
                    }

                }
        );
    }

}