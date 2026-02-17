class DummyClass_33736 {
    @Test
    public void test3() throws Exception {
        mockMvc.perform(
                (post("/fastjsonview/test3").characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))).andExpect(status
                ().isOk()).andDo(print()).andExpect(content().string("{\"id\":100,\"name\":\"æµè¯\",\"rootDepartment\":{\"description\":\"é¨é¨1æè¿°\"}}"));
    }

}