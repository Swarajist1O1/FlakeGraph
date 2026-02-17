class DummyClass_179447 {
    @Test
    public void testInsertList() {
        SqlSession sqlSession = getSqlSession();
        try {
            DemoCountryMapper mapper = sqlSession.getMapper(DemoCountryMapper.class);
            List<DemoCountry> countryList = new ArrayList<DemoCountry>();
            countryList.add(new DemoCountry("20", "Zimbabwe","ZW"));
            countryList.add(new DemoCountry("21", "Zaire","ZR"));
            countryList.add(new DemoCountry("22", "Zambia","ZM"));
            int updates = mapper.insertList(countryList);
            Assert.assertEquals(3, updates);
        } finally {
            //sqlSession.commit();
            sqlSession.close();
        }
    }

}