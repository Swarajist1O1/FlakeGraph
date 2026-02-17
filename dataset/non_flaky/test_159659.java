class DummyClass_159659 {
   @Test
   public void testDiffExternalForeignKeys() throws Exception {
       assumeNotNull(this.getDatabase());
       clearDatabase();
       Liquibase liquibase = createLiquibase(externalfkInitChangeLog);
       liquibase.update(contexts);

       DiffResult diffResult = liquibase.diff(database, null, new CompareControl());
       DiffResultAssert.assertThat(diffResult).containsMissingForeignKeyWithName("fk_person_country");
   }

}