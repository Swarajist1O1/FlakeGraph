class DummyClass_92674 {
    @Test
    public void testType() throws Exception {
        ArgumentConfig argument = new ArgumentConfig();
        argument.setType("int");
        assertThat(argument.getType(), equalTo("int"));
    }

}