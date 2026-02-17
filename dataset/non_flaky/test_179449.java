class DummyClass_179449 {
    @Test
    public void testByIdList() {
        SqlSession sqlSession = getSqlSession();
        try {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            List<Long> idList = Arrays.asList(1L, 2L, 3L);
            List<Country> countryList = mapper.selectByIdList(idList);
            Assert.assertEquals(3, countryList.size());
            Assert.assertEquals(1L, (long) countryList.get(0).getId());
            Assert.assertEquals(2L, (long) countryList.get(1).getId());
            Assert.assertEquals(3L, (long) countryList.get(2).getId());
            //å é¤
            Assert.assertEquals(3, mapper.deleteByIdList(idList));
            //æ¥è¯¢ç»æ0
            Assert.assertEquals(0, mapper.selectByIdList(idList).size());
        } finally {
            sqlSession.close();
        }
    }

}