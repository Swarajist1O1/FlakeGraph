class DummyClass_159655 {
    @Test
    public void testAbsolutePathChangeLog() throws Exception {
        assumeNotNull(this.getDatabase());

        String fileUrlToChangeLog = getClass().getResource("/" + includedChangeLog).toString();
        assertTrue(fileUrlToChangeLog.startsWith("file:/"));

        String absolutePathOfChangeLog = fileUrlToChangeLog.replaceFirst("file:\\/", "");
        if (System.getProperty("os.name").startsWith("Windows ")) {
            absolutePathOfChangeLog = absolutePathOfChangeLog.replace('/', '\\');
        } else {
            absolutePathOfChangeLog = "/" + absolutePathOfChangeLog;
        }
        Liquibase liquibase = createLiquibase(absolutePathOfChangeLog, new FileSystemResourceAccessor());
        clearDatabase();

        liquibase.update(this.contexts);

        liquibase.update(this.contexts); //try again, make sure there are no errors

        clearDatabase();
    }

}