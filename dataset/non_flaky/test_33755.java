class DummyClass_33755 {
    @Test
    public void test5() throws Exception {

        String jsonStr = "{\"packet\":{\"smsType\":\"USER_LOGIN\"}}";

        mockMvc.perform(
                (post("/fastjson/test5").characterEncoding("UTF-8").content(jsonStr)
                        .contentType(MediaType.APPLICATION_JSON))).andDo(print());
    }

}