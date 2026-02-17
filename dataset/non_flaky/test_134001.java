class DummyClass_134001 {
    @Test(timeout = 300000)
    public void oneNodeDownTest() {

        workflow(wf -> {
            wf.deploy();

            try {
                oneNodeDown(wf);
            } catch (InterruptedException e) {
                fail("Test failed", e);
            }
        });
    }

}