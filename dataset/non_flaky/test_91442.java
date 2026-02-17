class DummyClass_91442 {
@TestCaseOrdering(TestCaseOrdering.AlphabeticOrder.class)
    public void onlyCompatibleDistributions() {
        assumeTrue("only dpkg platforms", isDPKG());
        assumeTrue("only compatible distributions", distribution().packaging.compatible);
    }

}