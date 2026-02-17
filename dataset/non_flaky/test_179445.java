class DummyClass_179445 {
    @Test
    public void testMin() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            AggregateCondition aggregateCondition = AggregateCondition.builder().
                    aggregateBy("id").aliasName("aggregation").aggregateType(AggregateType.MIN);
            Example example = new Example(User.class);
            List<User> m = mapper.selectAggregationByExample(example, aggregateCondition);
            Assert.assertEquals(1, m.size());
            Assert.assertEquals(new Long(1), m.get(0).getAggregation());
        } finally {
            sqlSession.close();
        }
    }

}