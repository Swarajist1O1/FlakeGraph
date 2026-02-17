class DummyClass_33752 {
    @Test
    public void test3_2() throws Exception {
        ResultActions actions = this.mockMvc.perform(post("/fastjson/test3?jsonp=fnUpdateSome"));
        actions.andDo(print());
        actions.andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JAVASCRIPT))
                .andExpect(content().string("/**/fnUpdateSome({})"));
    }

}