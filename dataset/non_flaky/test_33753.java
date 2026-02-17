class DummyClass_33753 {
    @Test
    public void test4() throws Exception {

        String jsonStr = "{\"t\":{\"id\":123,\"name\":\"ååå\"}}";

        mockMvc.perform(
                (post("/fastjson/test4").characterEncoding("UTF-8").content(jsonStr)
                        .contentType(MediaType.APPLICATION_JSON))).andDo(print());
    }

}