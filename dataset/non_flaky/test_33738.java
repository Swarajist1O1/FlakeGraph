class DummyClass_33738 {
    @Test
    public void test5() throws Exception {
        mockMvc.perform(
                (post("/fastjsonview/test5").characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))).andExpect(status
                ().isOk()).andDo(print()).andExpect(content().string("{\"description\":\"fastjsonviewæ³¨è§£æµè¯\",\"id\":100,\"name\":\"æµè¯\",\"rootDepartment\":{\"children\":[],\"id\":1,\"members\":[],\"name\":\"é¨é¨1\"},\"stock\":\"haha\"}"));
    }

}