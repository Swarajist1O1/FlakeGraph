class DummyClass_91427 {
@TestLogging("org.elasticsearch.xpack.ml.action:DEBUG")
    public void resetLicensing() {
        enableLicensing();

        ensureStableCluster(1);
        ensureYellow();
    }

}