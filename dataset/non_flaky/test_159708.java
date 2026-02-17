class DummyClass_159708 {
    @Test
    public void shouldRunWhenShouldRunIsTrue() {
        System.setProperty("liquibase.shouldRun", "true");
        validateRunningState(true);
    }

}