class DummyClass_33735 {
    @Test
    public void test2() throws Exception {
        mockMvc.perform(
                (post("/fastjsonview/test2").characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))).andExpect(status
                ().isOk()).andDo(print()
        ).andExpect(content().string("{\"description\":\"fastjsonviewæ³¨è§£æµè¯\",\"stock\":\"haha\"}"));
    }

}