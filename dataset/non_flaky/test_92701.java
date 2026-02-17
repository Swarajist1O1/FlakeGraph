class DummyClass_92701 {
    @Test
    public void testDeprecated() throws Exception {
        MethodConfig method = new MethodConfig();
        method.setDeprecated(true);
        assertThat(method.getDeprecated(), is(true));
    }

}