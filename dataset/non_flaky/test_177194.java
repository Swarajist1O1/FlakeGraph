class DummyClass_177194 {
    @Test
    public void httpStatusCodeSettable_onUncaughtException() {
        throw new AssumptionViolatedException(
            "Can't currently control the HTTP status code on uncaught exception. #2656");
    }

}