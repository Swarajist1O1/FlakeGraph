class DummyClass_53140 {
    @Test
    public void testWithUser() throws Exception {
        controller.setInfo(new ClassPathResource("info.tmpl"));
        HashMap<String, Object> model = new HashMap<String, Object>();
        View view = controller.info(model, new UsernamePasswordAuthenticationToken(testAccounts.getUserName(), "<NONE>"));
        MockHttpServletResponse response = new MockHttpServletResponse();
        view.render(model, new MockHttpServletRequest(), response);
        String content = response.getContentAsString();
        assertTrue("Wrong content: " + content, content.contains("\n  \"user\": \""+testAccounts.getUserName()+"\""));
    }

}