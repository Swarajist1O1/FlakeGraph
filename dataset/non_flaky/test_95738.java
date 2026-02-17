class DummyClass_95738 {
    @Test
    public void shouldUpdateWithAutoCommitDisabled() {
        givenSomeDataSourceWithAutoCommitSetTo(false);
        whenTheFeatureIsEnabled();
        thenTheDatabaseShouldBeUpdated();
    }

}