class DummyClass_133992 {
    @Test
    public void testSerialize(){
        ByteBuf buf = Unpooled.buffer();

        Set<String> payload = new HashSet<>();
        payload.add("value1");
        payload.add("value2");

        CorfuProtocolCommon.serialize(buf, payload);
        assertThat(CorfuProtocolCommon.setFromBuffer(buf, String.class)).isEqualTo(payload);
    }

}