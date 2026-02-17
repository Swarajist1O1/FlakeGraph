class DummyClass_179510 {
    @Test
    public void testDynamicSelectAll() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        try {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            Country country = new Country();
            List<Country> countryList;
            //country.setDynamicTableName123("country_123");
            //countryList = mapper.select(country);
            //æ¥è¯¢æ»æ°
            //Assert.assertEquals(2, countryList.size());

            country.setDynamicTableName123(null);
            countryList = mapper.select(country);
            //æ¥è¯¢æ»æ°
            Assert.assertEquals(183, countryList.size());
        } finally {
            sqlSession.close();
        }
    }

}