class DummyClass_114094 {
    @Test
    public void toBuilder() {
        Key keyClone = key.toBuilder().build();

        assertThat(key, is(equalTo(keyClone)));
    }

}