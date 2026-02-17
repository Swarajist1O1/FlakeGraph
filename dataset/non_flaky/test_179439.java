class DummyClass_179439 {
    @Test
    public void testUpdateByPrimaryKeySelectiveForceByNull() {
        SqlSession sqlSession = getSqlSession();
        try {
            CountryIntMapper mapper = sqlSession.getMapper(CountryIntMapper.class);
            CountryInt country = new CountryInt();
            country.setId(174);
            country.setCountryname("è±å½");
            mapper.updateByPrimaryKeySelectiveForce(country, null);

            country = mapper.selectByPrimaryKey(174);
            Assert.assertNotNull(country.getCountrycode());
        } finally {
            sqlSession.close();
        }
    }

}