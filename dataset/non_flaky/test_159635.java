class DummyClass_159635 {
    @Test
    public void viewCreatedOnCorrectSchema() throws Exception {
        assumeNotNull(this.getDatabase());

        Liquibase liquibase = createLiquibase(this.viewOnSchemaChangeLog);
        clearDatabase();

        try {
            liquibase.update(this.contexts);
        } catch (ValidationFailedException e) {
            e.printDescriptiveError(System.out);
            throw e;
        }

        Statement queryIndex = ((JdbcConnection) this.getDatabase().getConnection()).getUnderlyingConnection().createStatement();

        ResultSet indexOwner = queryIndex.executeQuery("SELECT owner FROM ALL_VIEWS WHERE view_name = 'V_BOOK2'");

        assertTrue(indexOwner.next());

        String owner = indexOwner.getString("owner");

        assertEquals("LBCAT2", owner);

        // check that the automatically rollback now works too
        try {
            liquibase.rollback( new Date(0),this.contexts);
        } catch (ValidationFailedException e) {
            e.printDescriptiveError(System.out);
            throw e;
        }
    }

}