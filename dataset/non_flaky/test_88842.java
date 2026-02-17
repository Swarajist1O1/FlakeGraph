class DummyClass_88842 {
    @Test
    public void putRemoveScenarioNewMap() throws Exception {
        long seed = U.currentTimeMillis();
        doPutRemoveTest(seed, true, 30_000_000);
    }

}