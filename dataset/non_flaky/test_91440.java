class DummyClass_91440 {
@TestCaseOrdering(TestCaseOrdering.AlphabeticOrder.class)
    public void onlyCompatibleDistributions() {
        assumeTrue("only compatible distributions", distribution().packaging.compatible);
    }

}