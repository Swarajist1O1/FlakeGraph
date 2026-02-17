class DummyClass_33734 {
    @Test
    public void test1() throws Exception {
        mockMvc.perform(
                (post("/fastjsonview/test1").characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))).andExpect(status
                ().isOk()).andDo(print()
        ).andExpect(content().string("{\"id\":100,\"name\":\"æµè¯\"}"));
    }

}