class DummyClass_53139 {
    @Test
    public void testNoUser() throws Exception {
        controller.setInfo(new ClassPathResource("info.tmpl"));
        HashMap<String, Object> model = new HashMap<String, Object>();
        View view = controller.info(model, null);
        MockHttpServletResponse response = new MockHttpServletResponse();
        view.render(model, new MockHttpServletRequest(), response);
        String content = response.getContentAsString();
        assertFalse("Wrong content: " + content, content.contains("\"user\""));
    }

}