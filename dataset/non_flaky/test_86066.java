class DummyClass_86066 {
    @Test
    public void requiredPermissions() {
        assertThat(dbService.get("54e3deadbeefdeadbeefaffe")).get().satisfies(definition -> {
            assertThat(definition.config().requiredPermissions()).containsOnly("streams:read:stream-a", "streams:read:stream-b");
        });
    }

}