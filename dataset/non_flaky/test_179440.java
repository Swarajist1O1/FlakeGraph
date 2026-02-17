class DummyClass_179440 {
    @Test
    public void testUpdateByPrimaryKeySelectiveForce() {
        SqlSession sqlSession = getSqlSession();
        try {
            CountryIntMapper mapper = sqlSession.getMapper(CountryIntMapper.class);
            CountryInt country = new CountryInt();
            country.setId(174);
            mapper.updateByPrimaryKeySelectiveForce(country, Arrays.asList("countrycode", "countryname"));

            country = mapper.selectByPrimaryKey(174);
            Assert.assertNull(country.getCountrycode());
            Assert.assertNull(country.getCountryname());
        } finally {
            sqlSession.close();
        }
    }

}