class DummyClass_133991 {
    @Test
    public void testBuildPayloadFromBuffer(){
        final int value = 12345;
        ByteBuf payload = Unpooled.buffer().writeInt(value);
        Integer result = CorfuProtocolCommon.fromBuffer(payload, Integer.class);

        assertThat(result).isEqualTo(value);
    }

}