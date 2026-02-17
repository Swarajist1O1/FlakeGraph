class DummyClass_88841 {
    @Test
    public void putRemoveScenario() throws Exception {
        long seed = U.currentTimeMillis();

        doPutRemoveTest(seed, false, 1_000_000);
    }

}