class DummyClass_179466 {
    @Test
    public void testSelect() {
        SqlSession sqlSession = getSqlSession();
        try {
            TimeModelMapper mapper = sqlSession.getMapper(TimeModelMapper.class);
            List<TimeModel> list = mapper.selectAll();
            Assert.assertEquals(2, list.size());

            Assert.assertEquals("2018-01-01", toDate(list.get(0).getTestDate()));
            Assert.assertEquals("12:11:00", toTime(list.get(0).getTestTime()));
            Assert.assertEquals("2018-01-01 12:00:00", toDatetime(list.get(0).getTestDatetime()));

            Assert.assertEquals("2018-11-11", toDate(list.get(1).getTestDate()));
            Assert.assertEquals("01:59:11", toTime(list.get(1).getTestTime()));
            Assert.assertEquals("2018-02-12 17:58:12", toDatetime(list.get(1).getTestDatetime()));
        } finally {
            sqlSession.close();
        }
    }

}