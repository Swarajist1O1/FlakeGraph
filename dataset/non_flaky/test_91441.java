class DummyClass_91441 {
@TestCaseOrdering(TestCaseOrdering.AlphabeticOrder.class)
    public void onlyCompatibleDistributions() {
        assumeTrue("only compatible distributions", distribution().packaging.compatible);
    }

}