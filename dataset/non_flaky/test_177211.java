class DummyClass_177211 {
    @Test
        public void onSettingsRead(ChannelHandlerContext ctx, Http2Settings settings) {
            assertThat(settings.pushEnabled()).isFalse();
        }

}