class DummyClass_33745 {
    @Test
    public void test6() throws Exception {

        mockMvc.perform(
                (post("/fastjson/test6").characterEncoding("UTF-8")
                        .param("userId", "1234")
                        .param("flag", "0")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)))
                .andDo(print());
    }

}