class DummyClass_177217 {
    @Test
    public void empty_unpooled() {
        final StreamDecoder decoder = newDecoder();
        final HttpData data = decoder.decode(HttpData.empty());
        assertThat(data.isPooled()).isFalse();
    }

}