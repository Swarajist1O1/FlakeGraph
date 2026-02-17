class DummyClass_95737 {
    @Test
    public void shouldUpdateWithAutoCommitEnabled() {
        givenSomeDataSourceWithAutoCommitSetTo(true);
        whenTheFeatureIsEnabled();
        thenTheDatabaseShouldBeUpdated();
    }

}