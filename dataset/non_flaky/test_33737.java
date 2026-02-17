class DummyClass_33737 {
    @Test
    public void test4() throws Exception {
        mockMvc.perform(
                (post("/fastjsonview/test4").characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))).andExpect(status
                ().isOk()).andDo(print()).andExpect(content().string("{\"id\":100,\"name\":\"æµè¯\",\"rootDepartment\":{\"children\":[],\"id\":1,\"members\":[],\"name\":\"é¨é¨1\"}}"));
    }

}