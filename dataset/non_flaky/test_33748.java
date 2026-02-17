class DummyClass_33748 {
    @Test
    public void test1_2() throws Exception {

        JSONObject json = new JSONObject();

        json.put("id", 123);

        json.put("name", "ååå");

        ResultActions actions = mockMvc.perform((post("/fastjson/test1?callback=fnUpdateSome").characterEncoding(
                "UTF-8").content(json.toJSONString()).contentType(MediaType.APPLICATION_JSON)));
        actions.andDo(print());
        actions.andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JAVASCRIPT))
                .andExpect(content().string("/**/fnUpdateSome({\"name\":\"ååå\",\"id\":123})"));
    }

}