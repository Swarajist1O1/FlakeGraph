class DummyClass_33750 {
    @Test
    public void test2_2() throws Exception {

        String jsonStr = "[{\"name\":\"p1\",\"sonList\":[{\"name\":\"s1\"}]},{\"name\":\"p2\",\"sonList\":[{\"name\":\"s2\"},{\"name\":\"s3\"}]}]";

        ResultActions actions = mockMvc.perform((post("/fastjson/test2?jsonp=fnUpdateSome").characterEncoding("UTF-8")
                .content(jsonStr).contentType(MediaType.APPLICATION_JSON)));
        actions.andDo(print());
        actions.andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JAVASCRIPT))
                .andExpect(content().string("/**/fnUpdateSome({\"p1\":1,\"p2\":2})"));
    }

}