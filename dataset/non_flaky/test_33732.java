class DummyClass_33732 {
    @Test
    public void test8_2() throws Exception {
//        ResultActions actions = mockMvc.perform((post("/jsonp-fastjsonview/test8?callback=fnUpdateSome").characterEncoding(
//                "UTF-8")));
//        actions.andDo(print());
//        actions.andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JAVASCRIPT))
//                .andExpect(content().string("fnUpdateSome({\"id\":100,\"name\":\"æµè¯\"})"));

        MvcResult mvcResult = mockMvc.perform(post("/jsonp-fastjsonview/test8?callback=fnUpdateSome").characterEncoding("UTF-8"))
                .andExpect(request().asyncStarted())
                .andReturn();


        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(content().contentType(FastJsonHttpMessageConverter.APPLICATION_JAVASCRIPT))
                .andExpect(content().string("/**/fnUpdateSome({})"));
    }

}