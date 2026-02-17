class DummyClass_179438 {
    @Test
    public void testUpdateByDiffer() {
        SqlSession sqlSession = getSqlSession();
        try {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            Country old = mapper.selectByPrimaryKey(1L);
            //(1, 'Angola', 'AO', 1)
            Country newer = new Country();
            newer.setId(1L);
            newer.setCountryname("Newer");
            newer.setCountrycode("AO");
            int count = mapper.updateByDiffer(old, newer);
            Assert.assertEquals(1, count);
            old = mapper.selectByPrimaryKey(1L);
            Assert.assertEquals(1L, old.getId().longValue());
            Assert.assertEquals("Newer", old.getCountryname());
            Assert.assertEquals("AO", old.getCountrycode());
        } finally {
            sqlSession.close();
        }
    }

}