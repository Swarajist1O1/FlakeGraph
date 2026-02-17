class DummyClass_179469 {
    @Test
    public void testInsert2() {
        SqlSession sqlSession = getSqlSession();
        try {
            TimeModel2Mapper mapper = sqlSession.getMapper(TimeModel2Mapper.class);
            TimeModel2 timeModel = new TimeModel2();
            timeModel.setId(3);
            Date now = new Date();
            Timestamp now2 = new Timestamp(now.getTime());
            timeModel.setTestDate(now);
            timeModel.setTestTime(now);
            timeModel.setTestDatetime(now2);
            Assert.assertEquals(1, mapper.insert(timeModel));

            timeModel = mapper.selectByPrimaryKey(3);

            //ä¿å­åæ°æ®åºä¸­ä¸å­å¨æ¶é´é¨å
            Assert.assertEquals(toDate(now), toDate(timeModel.getTestDate()));
            Assert.assertEquals(toDate(now) + " 00:00:00", toDatetime(timeModel.getTestDate()));

            //æ¥æåæ¶é´é½æ
            Assert.assertEquals(toTime(now), toTime(timeModel.getTestTime()));
            Assert.assertEquals(toDatetime(now), toDatetime(timeModel.getTestTime()));

            Assert.assertEquals(toDatetime(now), toDatetime(timeModel.getTestDatetime()));
        } finally {
            sqlSession.close();
        }
    }

}