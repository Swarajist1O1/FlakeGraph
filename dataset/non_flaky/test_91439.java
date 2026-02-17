class DummyClass_91439 {
@TestCaseOrdering(TestCaseOrdering.AlphabeticOrder.class)
    public void onlyCompatibleDistributions() {
        assumeTrue("only rpm platforms", isRPM());
        assumeTrue("only compatible distributions", distribution().packaging.compatible);
    }

}