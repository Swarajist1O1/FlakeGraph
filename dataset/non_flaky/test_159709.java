class DummyClass_159709 {
    @Test
    public void shouldntRunWhenConfigShouldRunIsFalse() {
        System.setProperty("liquibase.config.shouldRun", "false");
        validateRunningState(false);
    }

}