class DummyClass_159668 {
    @Test
    public void impossibleDefaultSchema() {
        Exception caughtException = null;
        try {
            getDatabase().setDefaultSchemaName("lbuser");
        } catch (Exception ex) {
            caughtException = ex;
        }
        assertNotNull("Must not allow using a defaultSchemaName that is different from the DB user's login schema.",
            caughtException);

    }

}