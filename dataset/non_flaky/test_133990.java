class DummyClass_133990 {
    @Test
    public void checkConstructorMap() {
        List<Class<?>> types = Arrays.asList(
                Byte.class, Integer.class, Long.class, Boolean.class, Double.class, Float.class, String.class,
                Layout.class, CheckpointEntryType.class, UUID.class, byte[].class, ByteBuf.class
        );

        assertThat(CorfuProtocolCommon.getConstructorMap().keySet()).containsAll(types);
    }

}