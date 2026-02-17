class DummyClass_53151 {
    @Test
    public void keys_are_not_modifiable() {
        read_json(oldJson);
        exception.expect(UnsupportedOperationException.class);
        config.getKeys().clear();
    }

}