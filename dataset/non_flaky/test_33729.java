class DummyClass_33729 {
	@Test
	public void test2() throws Exception {
		
		String jsonStr = "[{\"name\":\"p1\",\"sonList\":[{\"name\":\"s1\"}]},{\"name\":\"p2\",\"sonList\":[{\"name\":\"s2\"},{\"name\":\"s3\"}]}]";
		
		mockMvc.perform(
				(post("/fastjson/test2").characterEncoding("UTF-8").content(jsonStr).contentType(MediaType.APPLICATION_JSON)
						))
//		.andExpect(status().isOk())
				.andDo(print());
	}

}