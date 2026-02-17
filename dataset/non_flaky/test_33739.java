class DummyClass_33739 {
    @Test
    public void test6() throws Exception {
        mockMvc.perform(
                (post("/fastjsonview/test6").characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))).andExpect(status
                ().isOk()).andDo(print()).andExpect(content().string("{\"id\":100}"));
    }

}