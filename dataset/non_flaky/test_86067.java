class DummyClass_86067 {
    @Test
    public void requiredPermissionsWithEmptyStreams() {
        assertThat(dbService.get("54e3deadbeefdeadbeefafff")).get().satisfies(definition -> {
            assertThat(definition.config().requiredPermissions()).containsOnly("streams:read");
        });
    }

}