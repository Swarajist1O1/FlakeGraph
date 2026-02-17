class DummyClass_176804 {
    @Test
    public void shouldNotInflateVarName() throws Exception {
        String headerName = "name";
        HttpResponseFacade mock = mock(HttpResponseFacade.class);
        Response response = mock(Response.class);
        when(response.getHeader(anyString())).thenReturn(headerName);
        when(mock.response()).thenReturn(response);
        ((HttpAssertionFacadeImpl) facade).facade = mock;

        world.put("id", "1");
        facade.varAssignedFromHeader("{(id)}", headerName);
        Optional<String> value = world.get("id");
        assertThat(value, CustomMatchers.equalToOptional(headerName));
    }

}