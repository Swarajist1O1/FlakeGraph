class DummyClass_159660 {
    @Test
    public void testInvalidIncludeDoesntBreakLiquibase() throws Exception {
        assumeNotNull(this.getDatabase());
        Liquibase liquibase = createLiquibase(invalidReferenceChangeLog);
        try {
            liquibase.update(new Contexts());
            fail("Did not fail with invalid include");
        } catch (ChangeLogParseException ignored) {
            //expected
        }

        LockService lockService = LockServiceFactory.getInstance().getLockService(database);
        assertFalse(lockService.hasChangeLogLock());
    }

}