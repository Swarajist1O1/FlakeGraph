class DummyClass_92718 {
    @Test
    public void testQueues() throws Exception {
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setQueues(10);
        assertThat(protocol.getQueues(), is(10));
    }

}