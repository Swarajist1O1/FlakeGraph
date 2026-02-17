class DummyClass_33747 {
    @Test
    public void test1() throws Exception {

        JSONObject json = new JSONObject();

        json.put("id", 123);

        json.put("name", "ååå");

        mockMvc.perform(
                (post("/fastjson/test1").characterEncoding("UTF-8").content(json.toJSONString())
                        .contentType(MediaType.APPLICATION_JSON))).andExpect(status().isOk()).andDo(print());
    }

}