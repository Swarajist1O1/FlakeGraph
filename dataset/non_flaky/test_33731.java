class DummyClass_33731 {
    @Test
    public void test8() throws Exception {
        mockMvc.perform(
                (post("/jsonp-fastjsonview/test8").characterEncoding("UTF-8")
                        .contentType(FastJsonHttpMessageConverter.APPLICATION_JAVASCRIPT))).andExpect(status().isOk()).andDo(print());
    }

}