class DummyClass_159710 {
    @Test
    public void shouldRunWhenConfigShouldRunIsTrue() {
        System.setProperty("liquibase.config.shouldRun", "true");
        validateRunningState(true);
    }

}