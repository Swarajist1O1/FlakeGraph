class DummyClass_33754 {
    @Test
    public void test4_2() throws Exception {

        String jsonStr = "{\"t\":{\"id\":123,\"name\":\"ååå\"}}";

        ResultActions actions = mockMvc.perform((post("/fastjson/test4?callback=myUpdate").characterEncoding("UTF-8")
                .content(jsonStr).contentType(MediaType.APPLICATION_JSON)));
        actions.andDo(print());
        actions.andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JAVASCRIPT))
                .andExpect(content().string("/**/myUpdate(\"{\\\"t\\\":{\\\"id\\\":123,\\\"name\\\":\\\"ååå\\\"}}\")"));
    }

}