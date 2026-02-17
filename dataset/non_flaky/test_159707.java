class DummyClass_159707 {
    @Test
    public void shouldntRunWhenShouldRunIsFalse() {
        System.setProperty("liquibase.shouldRun", "false");
        validateRunningState(false);
    }

}