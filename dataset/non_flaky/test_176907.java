class DummyClass_176907 {
  @Test
  public void testFunction() {
    Tuple2<Tuple2<Integer,Integer>,Double> tuple =
        new RatingToTupleDouble().call(new Rating(1, 2, 3.0));
    assertEquals(1, tuple._1()._1().intValue());
    assertEquals(2, tuple._1()._2().intValue());
    assertEquals(3.0, tuple._2().doubleValue());
  }

}