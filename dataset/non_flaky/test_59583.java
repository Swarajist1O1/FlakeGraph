class DummyClass_59583 {
	@Test
	public void sendWithImageTest() {
		Map<String, InputStream> map = new HashMap<>();
		map.put("testImage", FileUtil.getInputStream("f:/test/me.png"));
		MailUtil.sendHtml("hutool@foxmail.com", "æµè¯", "<h1>é®ä»¶æ¥èªHutoolæµè¯</h1><img src=\"cid:testImage\" />", map);
	}

}