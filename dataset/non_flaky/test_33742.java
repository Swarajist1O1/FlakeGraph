class DummyClass_33742 {
    @Test
    public void test3() throws Exception {
        List<Object> list = this.mockMvc.perform(post("/fastjson/test3"))
                .andReturn().getResponse().getHeaderValues("Content-Length");
        Assert.assertNotEquals(list.size(), 0);
    }

}